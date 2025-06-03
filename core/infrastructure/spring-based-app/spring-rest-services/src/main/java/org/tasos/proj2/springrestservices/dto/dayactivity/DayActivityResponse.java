package org.tasos.proj2.springrestservices.dto.dayactivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayActivityResponse {

    private Long id;
    private String amountFactor;
    private Boolean isLogged;
    private LocalDate logDate;
//    private DayActivityRecurringInfoResponse dayActivityRecurringInfo;
    private DayGymActivityInfoResponse dayGymActivityInfo;
    private String activityId;
    private String activityTypeId;
}