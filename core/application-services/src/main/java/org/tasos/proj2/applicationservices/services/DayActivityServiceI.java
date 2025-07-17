package org.tasos.proj2.applicationservices.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;

public interface DayActivityServiceI {

    void deleteAllDayActivitiesByLogDateAndUserName(DayActivityAggregate dayActivity);

    List<DayActivityAggregate> createDayActivitiesForDate(Optional<List<DayActivityAggregate>> dayActivities);

    void calculateNextExercises(List<DayActivityAggregate> dayActivities);

    List<DayActivityProjection> getDayActivitiesByDateAndUserName_WithExtraInfo(LocalDate date, String userName);

    List<DayActivityAggregate> findAllByLogDateAndActivitytype(LocalDate date, String activityTypeId);

    List<DayActivityAggregate> findAllByActivitytypeAndUserName(ActivityType type, String userName);

    Optional<DayActivityAggregate> findById(Long id);
    List<DayActivityAggregate> findAllByLogDateAndUserName(LocalDate date, String userName);

    List<DayActivityAggregate> findAllByUserNameWithActivityType(String userName);
}
