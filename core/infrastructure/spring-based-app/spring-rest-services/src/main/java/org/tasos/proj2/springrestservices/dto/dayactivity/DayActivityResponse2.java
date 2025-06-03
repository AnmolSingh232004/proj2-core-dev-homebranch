package org.tasos.proj2.springrestservices.dto.dayactivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Used in place on old proj2's DayActivityDTO temporarily
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayActivityResponse2 {

    private String id;
    private String activityId;
    private String activityTitle;
    private String activityTypeId;

    private String todoKg;
    private String todoSets;
    private String todoReps;
    @Pattern(message = "loggedReps must be a number", regexp="^[0-9]*$")
    @Size(min=1, max=2)
    private String loggedReps;

    @Pattern(message = "amountFactor must be a number", regexp = "^[0-9]*$")
    @Size(min = 1, max = 7)
    private String amountFactor;

    @NotNull
    private LocalDate logDate;

    // extras
    private String activityTypeTitle;
    private String activitySubType;

    // Removed as irrelevant with getAllDayActivitiesForDate (we dont save recurring stuff in DB)
//    @Valid
//    private DayActivityRecurringInfoResponse dayActivityRecurringInfo;

    //    private DayGymActivityInfoResponse dayGymActivityInfo;

    private String isLoggedd;
}