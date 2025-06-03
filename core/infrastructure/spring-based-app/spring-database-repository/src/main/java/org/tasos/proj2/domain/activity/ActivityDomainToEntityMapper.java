package org.tasos.proj2.domain.activity;

import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;



public class ActivityDomainToEntityMapper {

    public ActivityEntity activityToActivityEntity(ActivityAggregate activity) {

//        if (activity.getActivityRootEntity().getId() == null) {
//            return new ActivityEntity(
//                    activity.getActivityRootEntity().getTitle(),
//                    activity.getActivityRootEntity().getActivitySubType().getTitle(),
//                    new ActivityTypeEntity(
//                            activity.getActivityRootEntity().getActivityType().getId(),
//                            activity.getActivityRootEntity().getActivityType().getTitle(),
//                            activity.getActivityRootEntity().getActivityType().getDescription()
//                    )
//            );
//        }

        return new ActivityEntity(
                activity.getActivityRootEntity().getId(),
                activity.getActivityRootEntity().getTitle(),
                activity.getActivityRootEntity().getActivitySubType().getTitle(),
                new ActivityTypeEntity(
                        activity.getActivityRootEntity().getActivityType().getId(),
                        activity.getActivityRootEntity().getActivityType().getTitle(),
                        activity.getActivityRootEntity().getActivityType().getDescription()
                ),
               activity.getActivityRootEntity().getUserName()
        );
    }

    public ActivityAggregate activityEntityToActivity(ActivityEntity entity) {
        return new ActivityAggregate.ActivityBuilder()
                .withId(entity.getId())
                .withTitle(entity.getTitle())
                .withActivitySubType(entity.getActivitySubType())
                .withActivityType(entity.getActivitytype().getId(), entity.getActivitytype().getTitle(), entity.getActivitytype().getDescription())
                .withUserName(entity.getUserName())
                .build();
    }
}
