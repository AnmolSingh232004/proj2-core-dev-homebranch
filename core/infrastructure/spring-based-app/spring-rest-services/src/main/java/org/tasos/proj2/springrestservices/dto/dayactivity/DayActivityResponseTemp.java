package org.tasos.proj2.springrestservices.dto.dayactivity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayActivityResponseTemp {

    private Long id;
    private String todoKg;
    private String todoSets;
    private String todoReps;
    private String loggedReps;
    private String amountFactor;
    private LocalDate logDate;
    private ActivityType activitytype;
    private Activity activity;
    private Boolean isLogged;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActivityType {
        private Long id;
        private String title;
        private String description;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Activity {
        private Long id;
        private String title;
        private String activitySubType;
    }
}
