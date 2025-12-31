package org.tasos.proj2.springrestservices.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tasos.proj2.applicationservices.services.ActivityTypeServiceI;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.springrestservices.controller.util.HeaderUtil;
//import org.tasos.proj2.springrestservices.controller.util.auth.JWTUtils;
import org.tasos.proj2.springrestservices.dto.activity.ActivityTypeResponse;
import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivityResponse2;
import org.tasos.proj2.springrestservices.mapper.ActivityTypeDomainToResponseMapper;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = {"x"}) //urls removed
@RequestMapping()
@AllArgsConstructor(onConstructor = @__(@Inject))
public class ActivityTypeController {

    private final Logger logger = LoggerFactory.getLogger(ActivityTypeController.class);

    private final ActivityTypeServiceI activityTypeService;

    private final ActivityTypeDomainToResponseMapper mapper;

    @PostMapping()
    public ResponseEntity<String> createActivityType(@RequestBody String newActType) throws URISyntaxException {
        // Add JWT username
        String userName = "user";

        String actTypeCreated = activityTypeService.createActivityTypeForUser(newActType, userName);

        return ResponseEntity.created(null).body(actTypeCreated);
    }

    @GetMapping()
    public List<ActivityTypeResponse> getAllActivityTypesForUser() {
        // Add JWT username
        String userName = "user";
        List<ActivityType> userTypes = activityTypeService.getAllActivityTypesForUser(userName);

        List<ActivityTypeResponse> response = userTypes.stream()
          .filter(actType -> !"GYM".equals(actType.getTitle()))
          .map( actType -> this.mapper.mapToActivityTypeResponse(actType)).collect(Collectors.toList());

        return response;
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws Exception {
        activityTypeService.deleteActivityType(id);
        return ResponseEntity.noContent()
          .headers(HeaderUtil.createEntityDeletionAlert("applicationName", false, "ENTITY_NAME", id.toString()))
          .build();
    }

}
