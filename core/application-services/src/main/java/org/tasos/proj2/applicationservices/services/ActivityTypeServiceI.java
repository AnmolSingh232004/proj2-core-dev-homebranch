package org.tasos.proj2.applicationservices.services;

import java.util.List;

import org.tasos.proj2.domain.activity.ActivityType;

public interface ActivityTypeServiceI {

    String createActivityTypeForUser(String newActType, String userName);

    ActivityType createFullActivityTypeForUser(String newActType, String userName);

    List<ActivityType> getAllActivityTypesForUser(String userName);

    void deleteActivityType(Long id) throws Exception;
}
