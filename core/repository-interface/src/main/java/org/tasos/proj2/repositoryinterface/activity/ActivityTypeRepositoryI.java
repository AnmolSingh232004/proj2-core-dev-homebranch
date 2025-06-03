package org.tasos.proj2.repositoryinterface.activity;

import java.util.List;

import org.tasos.proj2.domain.activity.ActivityType;

public interface ActivityTypeRepositoryI {

    ActivityType findByTitle(String title);

    ActivityType saveNewActivityType(String type, String userName);

    List<ActivityType> findAllTypesForUser(String userName);

    void deleteById(Long id);
}
