package org.tasos.proj2.domain.dayactivity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.model.DayActivityEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DayActivityEntityRepository extends JpaRepository<DayActivityEntity, Long> {

    List<DayActivityEntity> save(List<DayActivityEntity> dayActivities);

    DayActivityEntity save(DayActivityEntity dayActivity);

    List<DayActivityEntity> findAllByLogDateAndUserName(LocalDate date, String userName);

    List<DayActivityEntity> findAllByLogDateAndActivitytype(LocalDate date, ActivityTypeEntity activityType);

    List<DayActivityEntity> findAllByActivitytypeAndUserName(ActivityTypeEntity type, String userName);

//    Optional<Reservation> findFirstByBookable_IdAndStartDateAfterOrderByStartDateAsc(String id, Instant date);

    Optional<DayActivityEntity> findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(Long activityId, LocalDate date);

    Optional<DayActivityEntity> findFirstByActivity_IdAndLogDateBeforeOrderByLogDateDesc(Long activityId, LocalDate date);

    List<DayActivityEntity> findAllByLogDateBetweenAndActivitytypeAndUserName(LocalDate startDate, LocalDate endDate, ActivityTypeEntity activityType, String userName);

    List<DayActivityEntity> deleteDayActivityByActivity_IdAndLogDateEqualsAndUserName(Long activityId, LocalDate date, String userName);

    Optional<DayActivityEntity> findDayActivityByActivity_IdAndLogDateEquals(Long activityId, LocalDate date);

    @Query("SELECT d FROM DayActivityEntity d JOIN FETCH d.activitytype a WHERE d.userName = :userName")
    List<DayActivityEntity> findAllByUserNameWithActivityType(@Param("userName") String userName);


    /*  FROM PROJ-2
    List<DayActivity> save(List<DayActivity> dayActivities);

    DayActivity save(DayActivity dayActivity);

    List<DayActivity> findAllByLogDateEquals(LocalDate date);

    List<DayActivity> findAllByLogDateAndActivitytype(LocalDate date, ActivityType activityType);

    List<DayActivity> findAllByActivitytype(ActivityType type);

//    Optional<Reservation> findFirstByBookable_IdAndStartDateAfterOrderByStartDateAsc(String id, Instant date);

    Optional<DayActivity> findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(Long activityId, LocalDate date);

    Optional<DayActivity> findFirstByActivity_IdAndLogDateBeforeOrderByLogDateDesc(Long activityId, LocalDate date);

    List<DayActivity> findAllByLogDateBetweenAndActivitytype(LocalDate startDate, LocalDate endDate, ActivityType activityType);

    List<DayActivity> deleteDayActivityByActivity_IdAndLogDateEquals(Long activityId, LocalDate date);

    Optional<DayActivity> findDayActivityByActivity_IdAndLogDateEquals(Long activityId, LocalDate date);

     */
}
