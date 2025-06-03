package org.tasos.proj2.domain.dayactivity;

// package-private because we dont want this to be visible to callers

import java.io.Serializable;

class DayGymActivityInfo implements Serializable {

    String loggedReps;

    String todoKg;

    String todoSets;

    String todoReps;

    DayGymActivityInfo(){}

    DayGymActivityInfo(String loggedReps, String todoKg, String todoSets, String todoReps) {
        this.loggedReps = loggedReps;
        this.todoKg = todoKg;
        this.todoSets = todoSets;
        this.todoReps = todoReps;
    }

}

