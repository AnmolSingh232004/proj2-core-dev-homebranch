package org.tasos.proj2.domain.dayactivity;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDate;

/**
 * DTO class which can be used by QueryDSL queries to select fields from different user related entities,
 *
 * @author aioannidis, PRODYNA SE
 * @version 0.1.0
 * @since 0.1.0
 */
public class DayActivityProjection {

    private Long id;
    private String amountFactor;
    private LocalDate loggedDate;
    private String loggedReps;
    private String todoKg;
    private String todoSets;
    private String todoReps;
    private Long activityId;
    private Long activityTypeId;
    private String activityTypeTitle;

    private String activityTitle;
    private String activitySubType;
    private Boolean isLogged;

    private String userName;

    public DayActivityProjection() {

    }

    @QueryProjection
    public DayActivityProjection(Long id, String amountFactor, LocalDate loggedDate, String loggedReps, String todoKg, String todoSets, String todoReps, Long activityId, Long activityTypeId,
                                 String activityTypeTitle, String activityTitle, String activitySubType, Boolean isLogged, String userName) {
        this.id = id;
        this.amountFactor = amountFactor;
        this.loggedDate = loggedDate;
        this.loggedReps = loggedReps;
        this.todoKg = todoKg;
        this.todoSets = todoSets;
        this.todoReps = todoReps;
        this.activityId = activityId;
        this.activityTypeId = activityTypeId;
        this.activityTypeTitle = activityTypeTitle;
        this.activityTitle = activityTitle;
        this.activitySubType = activitySubType;
        this.isLogged = isLogged;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmountFactor() {
        return amountFactor;
    }

    public void setAmountFactor(String amountFactor) {
        this.amountFactor = amountFactor;
    }

    public LocalDate getLoggedDate() {
        return loggedDate;
    }

    public void setLoggedDate(LocalDate loggedDate) {
        this.loggedDate = loggedDate;
    }

    public String getLoggedReps() {
        return loggedReps;
    }

    public void setLoggedReps(String loggedReps) {
        this.loggedReps = loggedReps;
    }

    public String getTodoKg() {
        return todoKg;
    }

    public void setTodoKg(String todoKg) {
        this.todoKg = todoKg;
    }

    public String getTodoSets() {
        return todoSets;
    }

    public void setTodoSets(String todoSets) {
        this.todoSets = todoSets;
    }

    public String getTodoReps() {
        return todoReps;
    }

    public void setTodoReps(String todoReps) {
        this.todoReps = todoReps;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Long activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public String getActivityTypeTitle() {
        return activityTypeTitle;
    }

    public void setActivityTypeTitle(String activityTypeTitle) {
        this.activityTypeTitle = activityTypeTitle;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivitySubType() {
        return activitySubType;
    }

    public void setActivitySubType(String activitySubType) {
        this.activitySubType = activitySubType;
    }

    public Boolean getLogged() {
        return isLogged;
    }

    public void setLogged(Boolean logged) {
        isLogged = logged;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}