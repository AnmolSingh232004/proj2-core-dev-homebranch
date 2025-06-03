package org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator;

import java.util.List;

import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivityResponse2;

public interface DayActivitiesPerTypeCalculator {

    DayActivitiesPerTypeExtendedArrayDTO calculate(List<DayActivityResponse2> dayActivities);

}