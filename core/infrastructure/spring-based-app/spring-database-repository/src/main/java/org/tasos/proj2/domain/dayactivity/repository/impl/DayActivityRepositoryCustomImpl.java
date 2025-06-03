package org.tasos.proj2.domain.dayactivity.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tasos.proj2.domain.activity.model.QActivityEntity;
import org.tasos.proj2.domain.activity.model.QActivityTypeEntity;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.domain.dayactivity.QDayActivityProjection;
import org.tasos.proj2.domain.dayactivity.model.QDayActivityEntity;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

/**
 * NOT USED !
 *
 * @author aioannidis, PRODYNA SE
 * @version 0.1.0
 * @since 0.1.0
 */
// NOT USED, only  DayActivityRepository
public class DayActivityRepositoryCustomImpl implements DayActivityRepositoryCustom {

    private static final Logger log = LoggerFactory.getLogger(DayActivityRepositoryCustomImpl.class);

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

        JPQLQuery<DayActivityExtendedDTO> query = new JPAQuery<>(em, HQLTemplates.DEFAULT);

        BooleanBuilder predicate1 = new BooleanBuilder();
        predicate1.and(dayActivity.activitytype.eq(activityType));

        BooleanBuilder predicate3 = new BooleanBuilder();
        predicate3.and(dayActivity.activitytype.eq(activity.activitytype));

        BooleanBuilder predicate2 = new BooleanBuilder();
        predicate2.and(dayActivity.logDate.eq(date)).and(dayActivity.userName.eq(userName));

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
    public List<DayActivityProjection> findDayActivitiesByDateRangeAndActTypeAndUserNameWithSubtype(LocalDate start, LocalDate end, String actTypeId, String userName) {
        return null;
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
