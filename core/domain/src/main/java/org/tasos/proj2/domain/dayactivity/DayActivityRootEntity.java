package org.tasos.proj2.domain.dayactivity;



import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

class DayActivityRootEntity implements Serializable {
    Long id;

    String amountFactor;

    Boolean isLogged;

    LocalDate logDate;

    DayGymActivityInfo dayGymActivityInfo;

    DayActivityRecurringInfo dayActivityRecurringInfo;
    String activityId;
    String activityTypeId;

    String userName;

    // Added for CalendarController - needed a activity type title
    String activityTypeTitle;

    DayActivityRootEntity(Long id, String amountFactor, Boolean isLogged, LocalDate logDate, String userName) {
        this.id = id;
        this.amountFactor = amountFactor;
        this.isLogged = isLogged;
        this.logDate = logDate;
        this.userName = userName;
    }

    public String getActivityTypeTitle() {
        return activityTypeTitle;
    }

    public void setActivityTypeTitle(String activityTypeTitle) {
        this.activityTypeTitle = activityTypeTitle;
    }

    void addActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    void addActivityTypeTitle(String activityTypeTitle) {
        this.activityTypeTitle = activityTypeTitle;
    }

    void addActivityId(String activityId) {
        this.activityId = activityId;
    }

    boolean isAcceptable() {
        if (activityId.equalsIgnoreCase("") || activityTypeId.equalsIgnoreCase("")) {
            return false;
        } else {
            return true;
        }
    }
}
