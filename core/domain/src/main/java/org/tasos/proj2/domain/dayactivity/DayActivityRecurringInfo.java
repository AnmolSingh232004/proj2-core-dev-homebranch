package org.tasos.proj2.domain.dayactivity;

// package-private because we dont want this to be visible to callers

import java.io.Serializable;

public class DayActivityRecurringInfo implements Serializable {

    private Integer recurEvery;

    private Integer recurPeriod;

    DayActivityRecurringInfo(Integer recurEvery, Integer recurPeriod) {
        this.recurEvery = recurEvery;
        this.recurPeriod = recurPeriod;
    }

    public Integer getRecurEvery() {
        return recurEvery;
    }
    public Integer getRecurPeriod() {
        return recurPeriod;
    }

}

