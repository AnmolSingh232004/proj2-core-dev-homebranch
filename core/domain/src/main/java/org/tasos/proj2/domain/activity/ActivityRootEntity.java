package org.tasos.proj2.domain.activity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

class ActivityRootEntity {
    final Long id;

    private String title;

    private ActivitySubType activitySubType;

    private ActivityType activityType;

    private String userName;

    ActivityRootEntity(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    Long getId() {
        return id;
    }

    String getTitle() {
        return title;
    }

    ActivitySubType getActivitySubType() {
        return activitySubType;
    }

    ActivityType getActivityType() {
        return activityType;
    }

    String getUserName() {
        return userName;
    }

    void addActivitySubType(ActivitySubType activitySubType) {
        this.activitySubType = activitySubType;
    }

    void addActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    void addUserName(String userName) {
        this.userName = userName;
    }

    boolean isAcceptable() {
        if (activitySubType.getTitle().equalsIgnoreCase("")) {
            return false;
        } else {
            return true;
        }
    }
}
