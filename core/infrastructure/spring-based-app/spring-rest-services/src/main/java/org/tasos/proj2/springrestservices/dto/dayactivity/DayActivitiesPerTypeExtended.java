package org.tasos.proj2.springrestservices.dto.dayactivity;

import java.util.List;

import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DayActivitiesPerTypeExtended {

    private List<DayActivityInfo2> dayActivities;
    private String category;
    private String categoryId;
    private List<ActivityResponse> activities;
}
