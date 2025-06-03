package org.tasos.proj2.springrestservices.mapper;

import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.springrestservices.dto.dayactivity.DeleteDayActivityRequest;
import org.tasos.proj2.springrestservices.dto.dayactivity.NewUpdateDayActivityRequest;

public class DayActivityRequestsToDomainMapper {

    public DayActivityAggregate newDayActivityRequestToDayActivity(NewUpdateDayActivityRequest newDayActivityRequest) {
        DayActivityAggregate aggr =  new DayActivityAggregate.DayActivityBuilder()
//                .withId(newDayActivityRequest.getId() != null ? newDayActivityRequest.getId() : null)
                .withAmountFactor(newDayActivityRequest.getAmountFactor())
                .withIsLogged(newDayActivityRequest.getIsLoggedd())
                .withLogDate(newDayActivityRequest.getLogDate())
                .withDayActivityRecurringInfo(
                        Integer.toString(newDayActivityRequest.getRecurringInfo() != null ?
                                newDayActivityRequest.getRecurringInfo().getRecurEvery() : 0),
                        Integer.toString(newDayActivityRequest.getRecurringInfo() != null ?
                                newDayActivityRequest.getRecurringInfo().getRecurPeriod() : 0))
                .withDayGymActivityInfo(
                        newDayActivityRequest.getDayGymActivityInfo() != null ?
                                newDayActivityRequest.getDayGymActivityInfo().getLoggedReps() : "0",
                        newDayActivityRequest.getDayGymActivityInfo() != null ?
                                newDayActivityRequest.getDayGymActivityInfo().getTodoKg() : "0",
                        newDayActivityRequest.getDayGymActivityInfo() != null ?
                        newDayActivityRequest.getDayGymActivityInfo().getTodoSets() : "0",
                        newDayActivityRequest.getDayGymActivityInfo() != null ?
                        newDayActivityRequest.getDayGymActivityInfo().getTodoReps() : "0")
                .withActivityId(newDayActivityRequest.getActivityId())
                .withActivityTypeId(newDayActivityRequest.getActivityTypeId())
                .withUserName(newDayActivityRequest.getUserName())
                .build();

        if (newDayActivityRequest.getId() != null) aggr.setId(newDayActivityRequest.getId());

        return  aggr;
    }

    public DayActivityAggregate deleteDayActivityRequestToDayActivity(DeleteDayActivityRequest deleteDayActivityRequest) {
        return new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(deleteDayActivityRequest.getActivityId()))
                .withLogDate(deleteDayActivityRequest.getLogDate())
                .withUserName(deleteDayActivityRequest.getUsername())
                .build();
    }

}
