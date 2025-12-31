package org.tasos.proj2.springrestservices.controller;

import Dto.CategoryDto;
import Dto.CategoryResponseDto;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.springrestservices.controller.util.HeaderUtil;
//import org.tasos.proj2.springrestservices.controller.util.auth.JWTUtils;
import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;
import org.tasos.proj2.springrestservices.dto.activity.NewActivityRequest;
import org.tasos.proj2.springrestservices.dto.activity.PatchActivityRequest;
import org.tasos.proj2.springrestservices.dto.activity.UpdateActivityRequest;
import org.tasos.proj2.springrestservices.dto.activity.extras.CreateActivityDTO;
import org.tasos.proj2.springrestservices.mapper.ActivityDomainToResponseMapper;
import org.tasos.proj2.springrestservices.mapper.ActivityRequestsToDomainMapper;

import javax.inject.Inject;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping() // controllers ip removed for security purposes on my fork
@AllArgsConstructor(onConstructor = @__(@Inject))
public class ActivityController {

    private final ActivityServiceI activityService;

    private final ActivityDomainToResponseMapper activityDomainToResponseMapper;

    private final ActivityRequestsToDomainMapper activityRequestsToDomainMapper;

    @PostMapping()
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody NewActivityRequest newActivityRequest) throws Exception {

        // Add JWT username
        String userName = "user";
        newActivityRequest.setUserName(userName);

        ActivityAggregate newActivity = activityService.createActivity(activityRequestsToDomainMapper.newActivityRequestToActivity(newActivityRequest));

        ActivityResponse response = activityDomainToResponseMapper.mapToActivityResponse(newActivity);

        return ResponseEntity.created(new URI("." + response.getId()))
          .headers(HeaderUtil.createEntityCreationAlert("applicationName", false, "ENTITY_NAME", response.getId()
            .toString()))
          .body(response);
    }

    @PutMapping()
    public ResponseEntity<ActivityResponse> updateActivity(@RequestBody UpdateActivityRequest updateActivityRequest) throws Exception {
        if (updateActivityRequest.getId() == null) {
            // todo
            //            throw new BadRequestAlertException("An existing activity should already have an ID", ENTITY_NAME, "idexists");
        }
        ActivityAggregate updatedActivity = activityService.updateActivity(activityRequestsToDomainMapper.updateActivityRequestToActivity(updateActivityRequest));

        ActivityResponse response = activityDomainToResponseMapper.mapToActivityResponse(updatedActivity);

        return ResponseEntity.created(new URI("/api/activities/" + response.getId()))
          .headers(HeaderUtil.createEntityUpdateAlert("applicationName", false, "ENTITY_NAME", response.getId()
            .toString()))
          .body(response);
    }

    @PatchMapping()
    public ResponseEntity<ActivityResponse> updateActivity(@PathVariable Long id, @RequestBody PatchActivityRequest patchActivityRequest) throws Exception {

        if (patchActivityRequest.getTitle() == null)
            return null;

        ActivityAggregate updatedActivity = activityService.patchActivity(id, patchActivityRequest.getTitle());

        ActivityResponse response = activityDomainToResponseMapper.mapToActivityResponse(updatedActivity);

        return ResponseEntity.created(new URI("/api/activities/" + response.getId()))
          .headers(HeaderUtil.createEntityUpdateAlert("applicationName", false, "ENTITY_NAME", response.getId()
            .toString()))
          .body(response);
    }

    @GetMapping()
    public List<ActivityResponse> getAllActivities() throws Exception {

        // Add JWT username
        String userName = "user";
        List<ActivityResponse> activitiesDTOs = activityService.getAllActivitiesPerUser(userName)
          .stream()
          .map(act -> this.activityDomainToResponseMapper.mapToActivityResponse(act))
          .collect(Collectors.toList());

        return activitiesDTOs;
    }

    /**
     * Get all GYM activities by user
     * @return
     */
    @GetMapping()
    public List<ActivityResponse> getAllGymActivities() throws Exception {
        // Add JWT username
        String userName = "user";
        List<ActivityResponse> gymActivities = activityService.getAllGymActivitiesByUser(userName)
          .stream()
          .map(act -> this.activityDomainToResponseMapper.mapToActivityResponse(act))
          .collect(Collectors.toList());
        return gymActivities;
    }

    /**
     * Get activities by activity type
     * @return
     */
    @GetMapping()
    public List<ActivityResponse> getExpenseActivitiesByType(@RequestParam(name = "type") String activityType) throws Exception {
        // Add JWT username
        String userName = "user";
        List<ActivityResponse> expActivities = activityService.getExpenseActivitiesByTypeAndUserName(activityType, userName)
          .stream()
          .map(act -> this.activityDomainToResponseMapper.mapToActivityResponse(act))
          .collect(Collectors.toList());
        return expActivities;
    }

    /**
     * Get activity SubTypes by activity type
     * @return
     */
    @GetMapping()
    public List<String> getSubTypesByActivityType(@PathVariable String typeId) throws Exception {
        // Add JWT username
        String userName = "user";
        List<String> subTypesOfActivityType = activityService.getSubTypesByActivityTypeAndUserName(typeId, userName);
        return subTypesOfActivityType;
    }

    /**
     * {@code GET  /activities/:id} : get the "id" activity.
     *
     * @param id the id of the activity to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the activity, or with status {@code 404 (Not Found)}.
     */
    @GetMapping()
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable Long id) throws Exception {
        ActivityResponse activity = activityDomainToResponseMapper.mapToActivityResponse(activityService.getActivity(id)
          .get());
        return ResponseEntity.ok(activity);
    }

    /**
     * {@code DELETE  /activities/:id} : delete the "id" activity.
     *
     * @param id the id of the activity to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping()
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) throws Exception {
        activityService.deleteActivity(id);
        return ResponseEntity.noContent()
          .headers(HeaderUtil.createEntityDeletionAlert("applicationName", false, "ENTITY_NAME", id.toString()))
          .build();
    }

    @PostMapping(path = "x")
    public ResponseEntity<String> createActivity(@RequestBody CreateActivityDTO createActivityDTO) throws Exception {

        String userName = "user";
        createActivityDTO.setUserName(userName);

        ActivityAggregate newActivity = activityService.createActivity(activityRequestsToDomainMapper.newCreateActivityRequestToActivity(createActivityDTO));

        // Assuming ActivityAggregate has a method getId() to get the ID of the created activity
        String activityId = Long.toString(newActivity.getId());

        return ResponseEntity.ok(activityId);
    }

    @GetMapping(path = "x")
    public ResponseEntity<CategoryResponseDto> getAllActivityGrouped() throws Exception {
        CategoryResponseDto categories = activityService.getUserActivitiesGrouped();
        return ResponseEntity.ok(categories);
    }

    @GetMapping(path = "x")
    public ResponseEntity<CategoryResponseDto> getAllActivityGroupedByCategoriesForUser() throws Exception {
        // Add JWT username
        String userName = "user";
        CategoryResponseDto categories = activityService.getUserActivitiesGroupedUser(userName);
        return ResponseEntity.ok(categories);
    }

}
