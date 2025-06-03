package org.tasos.proj2.springrestservices.dto.dayactivity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class NewUpdateDayActivityRequest {

    private Long id;
    private String amountFactor;
    private Boolean isLoggedd;
    private LocalDate logDate;
    private NewUpdateDayActivityRequest.DayActivityRecurringInfoRequest recurringInfo;
    private NewUpdateDayActivityRequest.DayGymActivityInfoRequest dayGymActivityInfo;
    private String activityId;
    private String activityTypeId;
    private String userName;

    @Setter
    @Getter
    public class DayActivityRecurringInfoRequest {
        private Integer recurEvery;
        private Integer recurPeriod;
    }

    @Setter
    @Getter
    public class DayGymActivityInfoRequest {
        String loggedReps;
        String todoKg;
        String todoSets;
        String todoReps;
    }
}
