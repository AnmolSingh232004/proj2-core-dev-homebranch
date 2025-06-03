package org.tasos.proj2.applicationservices.services;

import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayActivityServiceI {

    public void deleteAllDayActivitiesByLogDateAndUserName(DayActivityAggregate dayActivity);

    public List<DayActivityAggregate> createDayActivitiesForDate(Optional<List<DayActivityAggregate>> dayActivities);

    public void calculateNextExercises(List<DayActivityAggregate> dayActivities);

    List<DayActivityProjection> getDayActivitiesByDateAndUserName_WithExtraInfo(LocalDate date, String userName);

    List<DayActivityAggregate> findAllByLogDateAndActivitytype(LocalDate date, String activityTypeId);

    List<DayActivityAggregate> findAllByActivitytypeAndUserName(ActivityType type, String userName);

    Optional<DayActivityAggregate> findById(Long id);
    List<DayActivityAggregate> findAllByLogDateAndUserName(LocalDate date, String userName);

    List<DayActivityAggregate> findAllByUserNameWithActivityType(String userName);
}
