package org.tasos.proj2.domain.dayactivity;

import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;
import org.tasos.proj2.domain.dayactivity.model.DayActivityEntity;


public class DayActivityDomainToEntityMapper {

    public DayActivityEntity dayActivityToDayActivityEntity(DayActivityAggregate dayActivity) {
        return new DayActivityEntity(
                dayActivity.getDayActivityRootEntity().id != null ? dayActivity.getDayActivityRootEntity().id : null,
                dayActivity.getDayActivityRootEntity().dayGymActivityInfo.todoKg,
                dayActivity.getDayActivityRootEntity().dayGymActivityInfo.todoSets,
                dayActivity.getDayActivityRootEntity().dayGymActivityInfo.todoReps,
                dayActivity.getDayActivityRootEntity().dayGymActivityInfo.loggedReps,
                dayActivity.getDayActivityRootEntity().amountFactor,
                dayActivity.getDayActivityRootEntity().logDate,
                new ActivityTypeEntity(Long.parseLong(dayActivity.getDayActivityRootEntity().activityTypeId)),
                new ActivityEntity(Long.parseLong(dayActivity.getDayActivityRootEntity().activityId)),
                dayActivity.getDayActivityRootEntity().isLogged,
                dayActivity.getDayActivityRootEntity().userName
        );

    }

    public DayActivityAggregate dayActivityEntityToDayActivity(DayActivityEntity entity) {
        return new DayActivityAggregate.DayActivityBuilder()
                .withId(entity.getId())
                .withAmountFactor(entity.getAmountFactor())
                .withDayGymActivityInfo(entity.getLoggedReps(), entity.getTodoKg(), entity.getTodoSets(), entity.getTodoReps())
                .withIsLogged(entity.getIsLogged())
                .withLogDate(entity.getLogDate())
                .withActivityId(Long.toString(entity.getActivity().getId()))
                .withActivityTypeId(Long.toString(entity.getActivitytype().getId()))
                .withUserName(entity.getUserName())
                .withActivityTypeTitle(entity.getActivitytype().getTitle())
                .build();
    }
}
