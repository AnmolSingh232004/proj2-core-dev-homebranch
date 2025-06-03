package org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator;

import java.util.List;

import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivitiesPerTypeExtended;

public class DayActivitiesPerTypeExtendedArrayDTO {

    List<DayActivitiesPerTypeExtended> dayActivitiesPerTypeExtendedArray;

    public List<DayActivitiesPerTypeExtended> getDayActivitiesPerTypeExtendedArray() {
        return dayActivitiesPerTypeExtendedArray;
    }

    public void setDayActivitiesPerTypeExtendedArray(List<DayActivitiesPerTypeExtended> dayActivitiesPerTypeExtendedArray) {
        this.dayActivitiesPerTypeExtendedArray = dayActivitiesPerTypeExtendedArray;
    }
}
