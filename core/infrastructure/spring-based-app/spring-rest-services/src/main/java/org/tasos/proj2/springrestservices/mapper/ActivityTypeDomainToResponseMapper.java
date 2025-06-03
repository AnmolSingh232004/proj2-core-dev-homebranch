package org.tasos.proj2.springrestservices.mapper;

import org.springframework.stereotype.Component;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;
import org.tasos.proj2.springrestservices.dto.activity.ActivityTypeResponse;

public class ActivityTypeDomainToResponseMapper {

    public ActivityTypeResponse mapToActivityTypeResponse(ActivityType type) {
        return new ActivityTypeResponse(type.getId(), type.getTitle(), "");
    }

}
