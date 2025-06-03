package org.tasos.proj2.repositoryinterface.dayactivity;

import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface DayActivityRepositoryI extends DayActivityRepositoryCustom{

    List<DayActivityAggregate> save(List<DayActivityAggregate> dayActivities);

    DayActivityAggregate save(DayActivityAggregate dayActivity);

    List<DayActivityAggregate> findAllByLogDateEqualsAndUserName(LocalDate date, String userName);

    // todo check !!! ActivityType changed to String activityTypeId !!!!
    // Breaks the aggregate/ddd model
    List<DayActivityAggregate> findAllByLogDateAndActivitytype(LocalDate date, String activityTypeId);

    // todo check !!! ActivityType changed to String activityTypeId !!!!
    // Breaks the aggregate/ddd model
    List<DayActivityAggregate> findAllByActivitytypeAndUserName(String activityTypeId, String userName);

//    Optional<Reservation> findFirstByBookable_IdAndStartDateAfterOrderByStartDateAsc(String id, Instant date);

    Optional<DayActivityAggregate> findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(Long activityId, LocalDate date);

    Optional<DayActivityAggregate> findFirstByActivity_IdAndLogDateBeforeOrderByLogDateDesc(Long activityId, LocalDate date);

    List<DayActivityAggregate> findAllByLogDateBetweenAndActivitytypeAndUserName(LocalDate startDate, LocalDate endDate, String activityTypeId, String userName);

    List<DayActivityAggregate> deleteDayActivityByActivity_IdAndLogDateEqualsAndUserName(Long activityId, LocalDate date, String userName);

    Optional<DayActivityAggregate> findDayActivityByActivity_IdAndLogDateEquals(Long activityId, LocalDate date);

    Optional<DayActivityAggregate> findById(Long dayActivityId);

    List<DayActivityAggregate> findAllByUserNameWithActivityType(String userName);
}
