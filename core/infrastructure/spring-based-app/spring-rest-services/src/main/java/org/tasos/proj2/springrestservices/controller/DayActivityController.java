package org.tasos.proj2.springrestservices.controller;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.tasos.proj2.applicationservices.services.DayActivityServiceI;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.springrestservices.controller.util.HeaderUtil;
import org.tasos.proj2.springrestservices.controller.util.auth.JWTUtils;
import org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator.DayActivitiesPerTypeCalculator;
import org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator.DayActivitiesPerTypeExtendedArrayDTO;
import org.tasos.proj2.springrestservices.dto.dayactivity.*;
import org.tasos.proj2.springrestservices.mapper.DayActivityDomainToResponseMapper;
import org.tasos.proj2.springrestservices.mapper.DayActivityRequestsToDomainMapper;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"https://proj2.localhost", "https://localhost:4201", "https://localhost:9003", "https://157.230.113.41"})
@RequestMapping("/api/proj2")
@AllArgsConstructor(onConstructor = @__(@Inject))
public class DayActivityController {

    private final Logger logger = LoggerFactory.getLogger(DayActivityController.class);


    private final DayActivityServiceI dayActivityService;

    private final DayActivityDomainToResponseMapper dayActivityDomainToResponseMapper;

    private final DayActivityRequestsToDomainMapper dayActivityRequestsToDomainMapper;

    private DayActivitiesPerTypeCalculator dayActivitiesPerTypeCalculator;

    /**
     * Called each time with a specific activity type (gym, or car, or house...)
     *
     * @param dayActivityDtos will be either gym, or car, or else..
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/day-activities")
    @Secured({"ROLE_USER", "ROLE_PAID"})
    public ResponseEntity<List<DayActivityResponse>> createDayActivities(@NotNull @RequestBody List<@Valid NewUpdateDayActivityRequest> dayActivityDtos) throws Exception {
        // Add JWT username
        String userName = JWTUtils.getUserNameFromJWT();
        dayActivityDtos = dayActivityDtos.stream().map(dayAct -> { dayAct.setUserName(userName); return dayAct; }).collect(Collectors.toList());


//        log.debug("REST request to save DayActivities : {}", dayActivityDtos.size());
        List<DayActivityAggregate> dayacts = dayActivityDtos.stream().map(this.dayActivityRequestsToDomainMapper::newDayActivityRequestToDayActivity).collect(Collectors.toList());
        List<DayActivityAggregate> dayActivitiesCreated = dayActivityService.createDayActivitiesForDate(Optional.ofNullable(dayacts));

        //
        // GYM SPECIFIC - PJ-100 Calculate next exercises
        //TODO filter only gym dayactivityDtos
        if (dayActivityDtos.get(0).getDayGymActivityInfo() != null
                && dayActivityDtos.get(0).getActivityTypeId().equals("1")) {
            dayActivityService.calculateNextExercises(dayActivitiesCreated);
        }
        //
        List<DayActivityResponse> response = dayActivitiesCreated.stream().map( act -> this.dayActivityDomainToResponseMapper.mapToDayActivityResponse(act)).collect(Collectors.toList());

        return ResponseEntity.created(null).body(response);
    }

    /**
     * Delete all dayActivities by activityId, logDate and userName
     * @param dayActivityDTO
     * @return
     * @throws URISyntaxException
     */
    @PostMapping("/day-act-delete")
    public ResponseEntity<LocalDate> deleteAllDayActivities(@RequestBody DeleteDayActivityRequest dayActivityDTO) throws URISyntaxException {
//        log.debug("REST request to delete DayActivities by logDate");
        // Add JWT username to request DTO
        String userName = JWTUtils.getUserNameFromJWT();
        dayActivityDTO.setUsername(userName);

        DayActivityAggregate dayActAggr = this.dayActivityRequestsToDomainMapper.deleteDayActivityRequestToDayActivity(dayActivityDTO);
        dayActivityService.deleteAllDayActivitiesByLogDateAndUserName(dayActAggr);

        // Just return the date for which deletion was successful
//        DayActivityResponse response = dayActivityDomainToResponseMapper.mapToDayActivityResponse(dayAct);
//        return ResponseEntity.created(null).body(response);
        return ResponseEntity.created(null).body(dayActivityDTO.getLogDate());
    }

    /**
     * {@code PUT  /day-activities} : Updates an existing dayActivity.
     *
     * @param dayActivity the dayActivity to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dayActivity,
     * or with status {@code 400 (Bad Request)} if the dayActivity is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dayActivity couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/day-activities")
    public ResponseEntity<DayActivityResponse> updateDayActivity(@RequestBody NewUpdateDayActivityRequest dayActivity) throws URISyntaxException {
//        log.debug("REST request to update DayActivity : {}", dayActivity);
        if (dayActivity.getId() == null) {
//            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DayActivityAggregate dayActAggr = this.dayActivityRequestsToDomainMapper.newDayActivityRequestToDayActivity(dayActivity);
        List<DayActivityAggregate> dayActAggrList = Collections.singletonList(dayActAggr);
        List<DayActivityAggregate> result = dayActivityService.createDayActivitiesForDate(Optional.ofNullable(dayActAggrList));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", false, "ENTITY_NAME", dayActivity.getId().toString()))
                .body(dayActivityDomainToResponseMapper.mapToDayActivityResponse(result.get(0)));
    }

    /**
     * {@code GET  /day-activities} : get ALL dayActivities per date
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dayActivities in body.
     */
    @GetMapping("/day-activities")
    @Secured({"ROLE_USER", "ROLE_PAID"})
    public List<DayActivitiesPerTypeExtended> getAllDayActivitiesForDate(@RequestHeader Map<String, String> headers,
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("logDate") LocalDate localDate) {
        // Add JWT username
        String userName = JWTUtils.getUserNameFromJWT();

        headers.forEach((key, value) -> {
            logger.info(String.format("XXX Header '%s' = %s", key, value));
        });

//        log.debug("REST request to get all DayActivities by date");
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
//        LocalDate localDate = LocalDate.parse(logDate, formatter);
//        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

        // Service layer provides Domain, in this case DayActivityProjection
        List<DayActivityProjection> dayActEntities1 = dayActivityService.getDayActivitiesByDateAndUserName_WithExtraInfo(localDate, userName);

        List<DayActivityResponse2> response = dayActEntities1.stream().map( act -> this.dayActivityDomainToResponseMapper.mapToDayActivityResponse2(act)).collect(Collectors.toList());

        // DayActs/Category/Activities bundled - User has dayActs
        DayActivitiesPerTypeExtendedArrayDTO dto = this.dayActivitiesPerTypeCalculator.calculate(response);

        return dto.getDayActivitiesPerTypeExtendedArray();
    }

    /**
     * {@code GET  /day-activities/:id} : get the "id" dayActivity.
     *
     * @param id the id of the dayActivity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dayActivity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/day-activities/{id}")
    public ResponseEntity<DayActivityResponseTemp> getDayActivity(@PathVariable Long id) {
//        log.debug("REST request to get DayActivity : {}", id);
        Optional<DayActivityAggregate> dayActivity = dayActivityService.findById(id);
//        return ResponseUtil.wrapOrNotFound(Optional.of(dayActivityDomainToResponseMapper.mapToDayActivityResponseTemp(dayActivity.get())));
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", false, "ENTITY_NAME", dayActivity.get().getId().toString()))
                .body(dayActivityDomainToResponseMapper.mapToDayActivityResponseTemp(dayActivity.get()));
    }
}
