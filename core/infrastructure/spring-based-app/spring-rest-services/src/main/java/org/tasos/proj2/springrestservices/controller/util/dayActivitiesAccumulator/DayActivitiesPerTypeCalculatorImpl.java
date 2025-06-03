package org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.stereotype.Component;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivityResponse2;
import org.tasos.proj2.springrestservices.mapper.ActivityDomainToResponseMapper;

@Component
public class DayActivitiesPerTypeCalculatorImpl implements DayActivitiesPerTypeCalculator {

    public ActivityServiceI activityService;

    public ActivityDomainToResponseMapper activityDomainToResponseMapper;

    public DayActivitiesPerTypeCalculatorImpl(ActivityServiceI activityService, ActivityDomainToResponseMapper activityDomainToResponseMapper) {
        this.activityService = activityService;
        this.activityDomainToResponseMapper = activityDomainToResponseMapper;
    }

    @Override
    public DayActivitiesPerTypeExtendedArrayDTO calculate(List<DayActivityResponse2> dayActivities) {
        return dayActivities.stream().reduce(initialize(), new DayActivitiesPerTypeExtReducer(), (a1, a2) -> a1);
    }

    private DayActivitiesPerTypeExtendedArrayDTO initialize() {
        DayActivitiesPerTypeExtendedArrayDTO arrayDTO = new DayActivitiesPerTypeExtendedArrayDTO();
        arrayDTO.setDayActivitiesPerTypeExtendedArray(new ArrayList<>());
        return arrayDTO;
    }

    private class DayActivitiesPerTypeExtReducer implements BiFunction<DayActivitiesPerTypeExtendedArrayDTO, DayActivityResponse2, DayActivitiesPerTypeExtendedArrayDTO> {
        private DayActivitiesPerTypeAccumulator dayActivitiesPerTypeAccumulator;

        public DayActivitiesPerTypeExtReducer() {
            this.dayActivitiesPerTypeAccumulator = new DayActivitiesPerTypeAccumulator(activityService, activityDomainToResponseMapper);
        }

        @Override
        public DayActivitiesPerTypeExtendedArrayDTO apply(DayActivitiesPerTypeExtendedArrayDTO analysis, DayActivityResponse2 t) {
            dayActivitiesPerTypeAccumulator.accumulate(t);
            analysis.setDayActivitiesPerTypeExtendedArray(dayActivitiesPerTypeAccumulator.getTotals("not_used"));
            return analysis;
        }
    }

}