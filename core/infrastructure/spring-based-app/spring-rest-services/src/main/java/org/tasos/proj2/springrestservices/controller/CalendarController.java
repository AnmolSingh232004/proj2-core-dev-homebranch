package org.tasos.proj2.springrestservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.applicationservices.services.DayActivityServiceI;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.springrestservices.controller.util.CalendarUtil;
import org.tasos.proj2.springrestservices.controller.util.HeaderUtil;
//import org.tasos.proj2.springrestservices.controller.util.auth.JWTUtils;
import org.tasos.proj2.springrestservices.dto.calendar.ActivityTypeCalendarDisplayDTO;
import org.tasos.proj2.springrestservices.dto.calendar.EditPillDateDTO;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.awt.Color;
import java.util.Random;

@RestController
@CrossOrigin(origins = {"x"})
@RequestMapping()
public class CalendarController {

    private final Logger log = LoggerFactory.getLogger(CalendarController.class);

    private static final String ENTITY_NAME = "dayActivity";

    private String applicationName;

    private final DayActivityServiceI dayActivityService;

    private final ActivityServiceI activityService;

    public CalendarController(DayActivityServiceI dayActivityService,
                              ActivityServiceI activityService) {
        this.dayActivityService = dayActivityService;
        this.activityService = activityService;
    }

    @PostMapping()
    public ResponseEntity<EditPillDateDTO> editDatePill(@RequestBody @Valid EditPillDateDTO editPillDate) throws Exception {

        log.debug("REST request to edit date of pill (ex. when dragging)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate originalDate = LocalDate.parse(editPillDate.getOrigDate(), formatter);
        LocalDate targetNewDate = LocalDate.parse(editPillDate.getTargetNewDate(), formatter);
        ActivityType activityType = activityService.findByTitle(editPillDate.getActType());

        // Find all day-activities by original date and type
        List<DayActivityAggregate> dayActEntities = dayActivityService.findAllByLogDateAndActivitytype(originalDate, String.valueOf(activityType.getId()));
        dayActEntities.forEach(d -> d.setLogDate(targetNewDate));

//        dayActEntities.forEach(d -> dayActivityService.save(d));
        List<DayActivityAggregate> changed = dayActivityService.createDayActivitiesForDate(Optional.ofNullable(dayActEntities));
        editPillDate.setTargetNewDate(changed.get(0).getLogDate().toString());

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, editPillDate.getActType()))
                .body(editPillDate);
    }

    // Get pills for all types
    @GetMapping()
    public List<ActivityTypeCalendarDisplayDTO> getAllPillsForCalendar() throws Exception {
        log.debug("REST request to getAllPillsForCalendar");

        List<ActivityTypeCalendarDisplayDTO> allPillsUniques = new ArrayList<>();

        // Get all user's dayActivities
        List<DayActivityAggregate> dayActAggrs = dayActivityService.findAllByUserNameWithActivityType("user");

        // Group dayActivities on List of Lists, by type Id
        List<List<DayActivityAggregate>> groupedByActivityTypeId = new ArrayList<>();
        Map<Long, List<DayActivityAggregate>> activityTypeMap = new HashMap<>();
        for (DayActivityAggregate dayActivity : dayActAggrs) {
            long activityTypeId = dayActivity.getActivityTypeId();
            List<DayActivityAggregate> activityTypeList = activityTypeMap.getOrDefault(activityTypeId, new ArrayList<>());
            activityTypeList.add(dayActivity);
            activityTypeMap.put(activityTypeId, activityTypeList);
        }
        groupedByActivityTypeId.addAll(activityTypeMap.values());

        // parse dayActs with same Type
        for (List<DayActivityAggregate> dayActListGroup : groupedByActivityTypeId) {
            List<ActivityTypeCalendarDisplayDTO> pills = dayActListGroup.stream()
              .map(dayActAggr -> {
                  ActivityTypeCalendarDisplayDTO activityDTO = new ActivityTypeCalendarDisplayDTO();
                  activityDTO.setTitle(dayActAggr.getActivityTypeTitle());
                  activityDTO.setStart(dayActAggr.getLogDate());
                  activityDTO.setColor(getRandomColor());
                  return activityDTO;
              })
              .collect(Collectors.toList());

            // for each logDate, keep only 1 pill of each Type
            List<ActivityTypeCalendarDisplayDTO> pillsUniques = pills.stream().distinct().collect(Collectors.toList());

            allPillsUniques.addAll(pillsUniques);
        }

        return allPillsUniques;
    }


    private String getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        Color color = new Color(r, g, b);
        return "#" + Integer.toHexString(color.getRGB()).substring(2);
    }

}
