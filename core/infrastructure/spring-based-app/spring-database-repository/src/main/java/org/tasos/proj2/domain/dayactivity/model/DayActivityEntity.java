package org.tasos.proj2.domain.dayactivity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "day_activity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DayActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "todo_kg")
    private String todoKg;

    @Column(name = "todo_sets")
    private String todoSets;

    @Column(name = "todo_reps")
    private String todoReps;

    @Column(name = "logged_reps")
    private String loggedReps;

    @Column(name = "amount_factor")
    private String amountFactor;

    @Column(name = "date")
    private LocalDate logDate;

    @ManyToOne
    @JsonIgnoreProperties("dayActivities")
    private ActivityTypeEntity activitytype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("dayActivities")
    private ActivityEntity activity;

//    @Column(name = "isLogged2")
    private Boolean isLogged;

    @Column(name = "user")
    private String userName;
}