package org.tasos.proj2.springrestservices.mapper;

import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;
import org.tasos.proj2.springrestservices.dto.activity.ActivityTypeResponse;
import org.tasos.proj2.springrestservices.dto.dayactivity.*;

public class DayActivityDomainToResponseMapper {

    public DayActivityResponse mapToDayActivityResponse(DayActivityAggregate dayActivity) {
        return new DayActivityResponse(
                dayActivity.getId(),
                dayActivity.getAmountFactor(),
                dayActivity.getIsLogged(),
                dayActivity.getLogDate(),
//                new DayActivityRecurringInfoResponse(
//                        dayActivity.getRecurringInfo().getRecurEvery(),
//                        dayActivity.getRecurringInfo().getRecurPeriod()
//                ),
                new DayGymActivityInfoResponse(
                        dayActivity.getLoggedReps(),
                        dayActivity.getTodoKg(),
                        dayActivity.getTodoSets(),
                        dayActivity.getTodoReps()
                ),
                String.valueOf(dayActivity.getActivityId()),
                String.valueOf(dayActivity.getActivityTypeId())
        );
    }

    // DayActivityProjection to DayActivityResponse2(simulates DayActivityDTO of proj2) -> for getAllDayActivitiesForDate only
    public DayActivityResponse2 mapToDayActivityResponse2(DayActivityProjection dayActivityProjection) {
        return new DayActivityResponse2(
                String.valueOf(dayActivityProjection.getId()),
                String.valueOf(dayActivityProjection.getActivityId()),
                dayActivityProjection.getActivityTitle(),
                String.valueOf(dayActivityProjection.getActivityTypeId()),
                dayActivityProjection.getTodoKg(),
                dayActivityProjection.getTodoSets(),
                dayActivityProjection.getTodoReps(),
                dayActivityProjection.getLoggedReps(),
                dayActivityProjection.getAmountFactor(),
                dayActivityProjection.getLoggedDate(),
                dayActivityProjection.getActivityTypeTitle(),
                dayActivityProjection.getActivitySubType(),
                dayActivityProjection.getLogged().toString()
        );
    }

    public DayActivityResponseTemp mapToDayActivityResponseTemp(DayActivityAggregate dayActivity) {
        return new DayActivityResponseTemp(
                dayActivity.getId(),
                dayActivity.getGymInfo() != null ? dayActivity.getTodoKg() : "0",
                dayActivity.getGymInfo() != null ? dayActivity.getTodoSets() : "0",
                dayActivity.getGymInfo() != null ? dayActivity.getTodoReps() : "0",
                dayActivity.getGymInfo() != null ? dayActivity.getLoggedReps() : "0",
                dayActivity.getAmountFactor(),
                dayActivity.getLogDate(),
                new DayActivityResponseTemp.ActivityType(dayActivity.getActivityTypeId(), "", ""),
                new DayActivityResponseTemp.Activity(dayActivity.getActivityId(), "", ""),
                dayActivity.getIsLogged()
        );
    }

}
