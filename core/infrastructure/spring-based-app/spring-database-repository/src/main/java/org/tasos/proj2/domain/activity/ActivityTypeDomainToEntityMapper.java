package org.tasos.proj2.domain.activity;

import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;

public class ActivityTypeDomainToEntityMapper {

    public ActivityType activityTypeEntityToActivityType(ActivityTypeEntity activityType) {
        return new ActivityType(activityType.getId(), activityType.getTitle(), activityType.getDescription());
    }
}
