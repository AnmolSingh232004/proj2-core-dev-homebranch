package org.tasos.proj2.springrestservices.mapper;

import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.springrestservices.dto.activity.NewActivityRequest;
import org.tasos.proj2.springrestservices.dto.activity.UpdateActivityRequest;
import org.tasos.proj2.springrestservices.dto.activity.extras.CreateActivityDTO;

public class ActivityRequestsToDomainMapper {

    public ActivityAggregate newActivityRequestToActivity(NewActivityRequest newActivityRequest) {
        return new ActivityAggregate.ActivityBuilder()
//                .withId(newActivityRequest.getId())
                .withTitle(newActivityRequest.getTitle())
                .withActivitySubType(newActivityRequest.getActivitySubType())
                .withActivityType(
                        newActivityRequest.getActivitytype().getId(),
                        newActivityRequest.getActivitytype().getTitle(),
                        newActivityRequest.getActivitytype().getDescription())
                .withUserName(newActivityRequest.getUserName())
                .build();
    }

    public ActivityAggregate updateActivityRequestToActivity(UpdateActivityRequest updateActivityRequest) {
        return new ActivityAggregate.ActivityBuilder()
                .withId(updateActivityRequest.getId())
                .withTitle(updateActivityRequest.getTitle())
                .withActivitySubType(updateActivityRequest.getActivitySubType())
                .withActivityType(
                        updateActivityRequest.getActivitytype().getId(),
                        updateActivityRequest.getActivitytype().getTitle(),
                        updateActivityRequest.getActivitytype().getDescription())
                .withUserName(updateActivityRequest.getUserName())
                .build();
    }

    // extra
    public ActivityAggregate newCreateActivityRequestToActivity(CreateActivityDTO createActivityDTO) {
        return new ActivityAggregate.ActivityBuilder()
//                .withId(newActivityRequest.getId())
                .withTitle(createActivityDTO.getNewActName())
                .withActivitySubType( !"".equalsIgnoreCase(createActivityDTO.getNewSubType()) ? createActivityDTO.getNewSubType() : createActivityDTO.getSelectedSubType())
                .withActivityType(
                        Long.parseLong(createActivityDTO.getActTypeId()),
                       "",
                        "")
                .withUserName(createActivityDTO.getUserName())
                .build();
    }
}
