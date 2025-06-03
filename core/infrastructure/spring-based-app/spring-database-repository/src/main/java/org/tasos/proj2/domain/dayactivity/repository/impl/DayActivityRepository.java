package org.tasos.proj2.domain.dayactivity.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;
import org.tasos.proj2.domain.activity.model.QActivityEntity;
import org.tasos.proj2.domain.activity.model.QActivityTypeEntity;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityDomainToEntityMapper;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.domain.dayactivity.QDayActivityProjection;
import org.tasos.proj2.domain.dayactivity.model.DayActivityEntity;
import org.tasos.proj2.domain.dayactivity.model.QDayActivityEntity;
import org.tasos.proj2.domain.dayactivity.repository.DayActivityEntityRepository;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This extra DAO layer exists to link repositories with Domain (Aggregates)
 *
 *
 */
public class DayActivityRepository implements DayActivityRepositoryI {

    private final DayActivityDomainToEntityMapper dayActivityDomainToEntityMapper;
    private final DayActivityEntityRepository dayActivityEntityRepository;

    @Inject
    public DayActivityRepository(DayActivityDomainToEntityMapper dayActivityDomainToEntityMapper, DayActivityEntityRepository dayActivityEntityRepository) {
        this.dayActivityDomainToEntityMapper = dayActivityDomainToEntityMapper;
        this.dayActivityEntityRepository = dayActivityEntityRepository;
    }

    @Override
    public DayActivityAggregate save(DayActivityAggregate dayActivity) {
        return dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayActivityEntityRepository.save(dayActivityDomainToEntityMapper.dayActivityToDayActivityEntity(dayActivity)));
    }

    @Override
    public List<DayActivityAggregate> save(List<DayActivityAggregate> dayActivities) {
        List<DayActivityAggregate> returnList = dayActivities.stream()
                .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayActivityEntityRepository.save(dayActivityDomainToEntityMapper.dayActivityToDayActivityEntity(dayAct))))
                .collect(Collectors.toList());

        return returnList;
    }

    @Override
    public List<DayActivityAggregate> findAllByLogDateEqualsAndUserName(LocalDate date, String userName) {
        List<DayActivityEntity> dayActivityEntities = dayActivityEntityRepository.findAllByLogDateAndUserName(date, userName);
        return dayActivityEntities.stream()
                .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayAct))
                .collect(Collectors.toList());
    }

    @Override
    public List<DayActivityAggregate> findAllByLogDateAndActivitytype(LocalDate date, String activityTypeId) {
        List<DayActivityEntity> dayActivityEntities = dayActivityEntityRepository.findAllByLogDateAndActivitytype(date, new ActivityTypeEntity(Long.parseLong(activityTypeId)));
        return dayActivityEntities.stream()
                .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayAct))
                .collect(Collectors.toList());
    }

    @Override
    public List<DayActivityAggregate> findAllByActivitytypeAndUserName(String activityTypeId, String userName) {
        List<DayActivityEntity> dayActivityEntities = dayActivityEntityRepository.findAllByActivitytypeAndUserName(new ActivityTypeEntity(Long.parseLong(activityTypeId)), userName);
        return dayActivityEntities.stream()
                .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayAct))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DayActivityAggregate> findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(Long activityId, LocalDate date) {
        DayActivityAggregate dayActivityAggregate = dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayActivityEntityRepository.findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(activityId, date).get());
        return Optional.of(dayActivityAggregate);
    }

    @Override
    public Optional<DayActivityAggregate> findFirstByActivity_IdAndLogDateBeforeOrderByLogDateDesc(Long activityId, LocalDate date) {
        DayActivityAggregate dayActivityAggregate = dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayActivityEntityRepository.findFirstByActivity_IdAndLogDateBeforeOrderByLogDateDesc(activityId, date).get());
        return Optional.of(dayActivityAggregate);
    }

    @Override
    public List<DayActivityAggregate> findAllByLogDateBetweenAndActivitytypeAndUserName(LocalDate startDate, LocalDate endDate, String activityTypeId, String userName) {
        List<DayActivityEntity> dayActivityEntities = dayActivityEntityRepository.findAllByLogDateBetweenAndActivitytypeAndUserName(startDate, endDate, new ActivityTypeEntity(Long.parseLong(activityTypeId)), userName);
        return dayActivityEntities.stream()
                .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayAct))
                .collect(Collectors.toList());
    }

    @Override
    public List<DayActivityAggregate> deleteDayActivityByActivity_IdAndLogDateEqualsAndUserName(Long activityId, LocalDate date, String userName) {
        List<DayActivityEntity> dayActivityEntities = dayActivityEntityRepository.deleteDayActivityByActivity_IdAndLogDateEqualsAndUserName(activityId, date, userName);
        return dayActivityEntities.stream()
                .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayAct))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DayActivityAggregate> findDayActivityByActivity_IdAndLogDateEquals(Long activityId, LocalDate date) {
        Optional<DayActivityEntity> dayActivityEntity = dayActivityEntityRepository.findDayActivityByActivity_IdAndLogDateEquals(activityId, date);
        return Optional.of(dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayActivityEntity.get()));
    }

    @Override
    public Optional<DayActivityAggregate> findById(Long dayActivityId) {
        Optional<DayActivityEntity> dayActivityEntity = dayActivityEntityRepository.findById(dayActivityId);
        return Optional.of(dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayActivityEntity.get()));
    }

    @Override
    public List<DayActivityAggregate> findAllByUserNameWithActivityType(String userName) {
        List<DayActivityEntity> entities = dayActivityEntityRepository.findAllByUserNameWithActivityType(userName);

        return entities.stream()
          .map(dayAct -> dayActivityDomainToEntityMapper.dayActivityEntityToDayActivity(dayAct))
          .collect(Collectors.toList());
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<DayActivityProjection> findDayActivitiesByDateAndUserNameWithActivityTypeTitle(LocalDate date, String userName) {
        QDayActivityEntity dayActivity = QDayActivityEntity.dayActivityEntity;
        QActivityEntity activity = QActivityEntity.activityEntity;
        QActivityTypeEntity activityType = QActivityTypeEntity.activityTypeEntity;

        FactoryExpression<DayActivityProjection> factoryExpression = new QDayActivityProjection(
                dayActivity.id,
                dayActivity.amountFactor,
                dayActivity.logDate,
                dayActivity.loggedReps,
                dayActivity.todoKg,
                dayActivity.todoSets,
                dayActivity.todoReps,
                dayActivity.activity.id,
                dayActivity.activitytype.id,
                activityType.title,
                activity.title,
                activity.activitySubType,
                dayActivity.isLogged,
                dayActivity.userName
        );

//        Long id;
//        String amountFactor;
//        String loggedDate;
//        String loggedReps;
//        String todoKg;
//        String todoSets;
//        String todoReps;
//        Long activityId;
//        Long activityTypeId;
//        String activityTypeTitle;

        JPQLQuery<DayActivityRepositoryCustomImpl.DayActivityExtendedDTO> query = new JPAQuery<>(em, HQLTemplates.DEFAULT);

        BooleanBuilder predicate1 = new BooleanBuilder();
        predicate1.and(dayActivity.activitytype.eq(activityType));

        BooleanBuilder predicate3 = new BooleanBuilder();
        predicate3.and(dayActivity.activitytype.eq(activity.activitytype));

        BooleanBuilder predicate2 = new BooleanBuilder();
        predicate2.and(dayActivity.logDate.eq(date)).and(dayActivity.userName.eq(userName));

//        BooleanBuilder predicate4 = new BooleanBuilder();
//        predicate4.and(dayActivity.userName.eq(userName));

        // Create the FROM and JOIN query parts
        query.from(dayActivity)
                .join(dayActivity.activitytype, activityType)
//            .leftJoin(userExtra)
                .on(predicate1)
                .join(dayActivity.activity, activity)
                .on(predicate3)
                .where(predicate2);


        // Add query criteria
//        query.where(user.login.eq(userLogin));

        // Add Select columns
        return query.select(factoryExpression).fetch();
    }

    @Override
    public List<DayActivityProjection> findDayActivitiesByDateAndUserNameWithActivityTypeTitleAndActTitle(LocalDate date, String userName) {
        QDayActivityEntity dayActivity = QDayActivityEntity.dayActivityEntity;
        QActivityEntity activity = QActivityEntity.activityEntity;
        QActivityTypeEntity activityType = QActivityTypeEntity.activityTypeEntity;

        FactoryExpression<DayActivityProjection> factoryExpression = new QDayActivityProjection(
          dayActivity.id,
          dayActivity.amountFactor,
          dayActivity.logDate,
          dayActivity.loggedReps,
          dayActivity.todoKg,
          dayActivity.todoSets,
          dayActivity.todoReps,
          dayActivity.activity.id,
          dayActivity.activitytype.id,
          activityType.title,
          activity.title,
          activity.activitySubType,
          dayActivity.isLogged,
          dayActivity.userName
        );

        JPQLQuery<DayActivityRepositoryCustomImpl.DayActivityExtendedDTO> query = new JPAQuery<>(em, HQLTemplates.DEFAULT);

        BooleanBuilder predicate1 = new BooleanBuilder();
        predicate1.and(dayActivity.activitytype.eq(activityType));

        BooleanBuilder predicate3 = new BooleanBuilder();
        predicate3.and(dayActivity.activitytype.eq(activity.activitytype));

        BooleanBuilder predicate2 = new BooleanBuilder();
        predicate2.and(dayActivity.logDate.eq(date)).and(dayActivity.userName.eq(userName));

        //        BooleanBuilder predicate4 = new BooleanBuilder();
        //        predicate4.and(dayActivity.userName.eq(userName));

        // Create the FROM and JOIN query parts
        query.from(dayActivity)
          .join(dayActivity.activitytype, activityType)
          //            .leftJoin(userExtra)
          .on(predicate1)
          .join(dayActivity.activity, activity)
          .on(predicate3)
          .where(predicate2);


        // Add query criteria
        //        query.where(user.login.eq(userLogin));

        // Add Select columns
        return query.select(factoryExpression).fetch();
    }

    @Override
    public List<DayActivityProjection> findDayActivitiesByDateRangeAndActTypeAndUserNameWithSubtype(
      LocalDate start, LocalDate end, String actTypeId, String userName) {

        // Define entity instances for query
        QDayActivityEntity dayActivity = QDayActivityEntity.dayActivityEntity;
        QActivityEntity activity = QActivityEntity.activityEntity;
        QActivityTypeEntity activityType = QActivityTypeEntity.activityTypeEntity;

        // Define the factory expression to select required fields
        FactoryExpression<DayActivityProjection> factoryExpression = new QDayActivityProjection(
          dayActivity.id,
          dayActivity.amountFactor,
          dayActivity.logDate,
          dayActivity.loggedReps,
          dayActivity.todoKg,
          dayActivity.todoSets,
          dayActivity.todoReps,
          dayActivity.activity.id,
          dayActivity.activitytype.id,
          activityType.title,
          activity.title, // Only include fields from activity
          activity.activitySubType,
          dayActivity.isLogged,
          dayActivity.userName
        );

        // Initialize the query
        JPQLQuery<DayActivityProjection> query = new JPAQuery<>(em, HQLTemplates.DEFAULT);

        // Create predicates for filtering
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(dayActivity.logDate.between(start, end));
        predicate.and(dayActivity.activitytype.id.eq(Long.parseLong(actTypeId)));
        predicate.and(dayActivity.userName.eq(userName));

        BooleanBuilder predicate1 = new BooleanBuilder();
        predicate1.and(dayActivity.activitytype.eq(activityType));

        // Construct the query with necessary joins and predicates
        query.from(dayActivity)

          .join(dayActivity.activitytype, activityType)
          .on(predicate1)

          .join(dayActivity.activity, activity)
          .where(predicate);

        // Execute the query and return results
        return query.select(factoryExpression).fetch();
    }


    public class DayActivityExtendedDTO {

        Long id;
        String amountFactor;
        String loggedDate;
        String loggedReps;
        String todoKg;
        String todoSets;
        String todoReps;
        Long activityId;
        Long activityTypeId;
        String activityTypeTitle;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getAmountFactor() {
            return amountFactor;
        }

        public void setAmountFactor(String amountFactor) {
            this.amountFactor = amountFactor;
        }

        public String getLogDate() {
            return loggedDate;
        }

        public void setLogDate(String logDate) {
            this.loggedDate = logDate;
        }

        public String getLoggedReps() {
            return loggedReps;
        }

        public void setLoggedReps(String loggedReps) {
            this.loggedReps = loggedReps;
        }

        public String getTodoKg() {
            return todoKg;
        }

        public void setTodoKg(String todoKg) {
            this.todoKg = todoKg;
        }

        public String getTodoSets() {
            return todoSets;
        }

        public void setTodoSets(String todoSets) {
            this.todoSets = todoSets;
        }

        public String getTodoReps() {
            return todoReps;
        }

        public void setTodoReps(String todoReps) {
            this.todoReps = todoReps;
        }

        public Long getActivityId() {
            return activityId;
        }

        public void setActivityId(Long activityId) {
            this.activityId = activityId;
        }

        public Long getActivityTypeId() {
            return activityTypeId;
        }

        public void setActivityTypeId(Long activityTypeId) {
            this.activityTypeId = activityTypeId;
        }

        public String getActivityTypeTitle() {
            return activityTypeTitle;
        }

        public void setActivityTypeTitle(String activityTypeTitle) {
            this.activityTypeTitle = activityTypeTitle;
        }
    }
}
