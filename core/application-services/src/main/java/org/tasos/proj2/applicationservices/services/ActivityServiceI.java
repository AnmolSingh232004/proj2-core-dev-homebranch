package org.tasos.proj2.applicationservices.services;

import java.util.List;
import java.util.Optional;

import Dto.CategoryDto;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.activity.CreateActivityDTO;

public interface ActivityServiceI {

    ActivityAggregate createActivity(ActivityAggregate activity) throws Exception;

    ActivityAggregate updateActivity(ActivityAggregate activity) throws Exception;

    ActivityAggregate patchActivity(Long id, String title) throws Exception;

    List<ActivityAggregate> getAllGymActivities() throws Exception;

    List<ActivityAggregate> getAllGymActivitiesByUser(String userName) throws Exception;

    List<ActivityAggregate> getExpenseActivitiesByType(String activityType) throws Exception;

    List<ActivityAggregate> getExpenseActivitiesByTypeAndUserName(String activityType, String userName) throws Exception;

    List<String> getSubTypesByActivityTypeAndUserName(String typeId, String userName) throws Exception;

    List<String> getSubTypesByActivityType(String typeId) throws Exception;

    Optional<ActivityAggregate> getActivity(Long id) throws Exception;

    void deleteActivity(Long id) throws Exception;

    void createActivity(CreateActivityDTO createActivityDTO) throws Exception;

    ActivityType findByTitle(String title) throws Exception;

    List<ActivityAggregate> getAllActivitiesPerUser(String userName) throws Exception;

    List<CategoryDto> getUserActivitiesGrouped() throws Exception;
}
