package org.tasos.proj2.domain.dayactivity;


import org.tasos.proj2.domain.activity.ActivityAggregate;

import java.io.Serializable;
import java.time.LocalDate;

//@Aggregate
public class DayActivityAggregate implements Serializable {
    private DayActivityRootEntity dayActivityRootEntity;

    DayActivityRootEntity getDayActivityRootEntity() {
        return dayActivityRootEntity;
    }

    // todo check why protected doesn't work
//    void test(ActivityAggregate aggr) {
//        aggr.get
//    }

    private DayActivityAggregate(DayActivityBuilder builder) {
        this.dayActivityRootEntity = new DayActivityRootEntity(builder.id, builder.amountFactor, builder.isLogged, builder.logDate, builder.userName);
        this.dayActivityRootEntity.addActivityId(builder.activityId);
        this.dayActivityRootEntity.addActivityTypeId(builder.activityTypeId);
        this.dayActivityRootEntity.addActivityTypeTitle(builder.activityTypeTitle);
        this.dayActivityRootEntity.dayGymActivityInfo = builder.dayGymActivityInfo;
        this.dayActivityRootEntity.dayActivityRecurringInfo = builder.dayActivityRecurringInfo;

    }

    public String getActivityTypeTitle() {return  dayActivityRootEntity.activityTypeTitle; }
    public String getAmountFactor() { return dayActivityRootEntity.amountFactor; }
    public Boolean getIsLogged() { return dayActivityRootEntity.isLogged; }
    public LocalDate getLogDate() { return dayActivityRootEntity.logDate; }
    public void setLogDate(LocalDate date) { dayActivityRootEntity.logDate = date; }
    public Long getId() { return dayActivityRootEntity.id; }
    public void setId(Long id) { dayActivityRootEntity.id = id; }
    public Long getActivityId() { return Long.parseLong(dayActivityRootEntity.activityId); }
    public Long getActivityTypeId() { return Long.parseLong(dayActivityRootEntity.activityTypeId); }
    public DayActivityRecurringInfo getRecurringInfo() { return dayActivityRootEntity.dayActivityRecurringInfo; }
    public DayGymActivityInfo getGymInfo() { return dayActivityRootEntity.dayGymActivityInfo; }
    public String getTodoKg() { return dayActivityRootEntity.dayGymActivityInfo.todoKg; }
    public void setTodoKg(String todoKg) { dayActivityRootEntity.dayGymActivityInfo.todoKg = todoKg; }
    public String getTodoReps() { return dayActivityRootEntity.dayGymActivityInfo.todoReps; }
    public void setTodoReps(String todoReps) { dayActivityRootEntity.dayGymActivityInfo.todoReps = todoReps; }
    public String getTodoSets() { return dayActivityRootEntity.dayGymActivityInfo.todoSets; }
    public void setTodoSets(String todoSets) { dayActivityRootEntity.dayGymActivityInfo.todoSets = todoSets; }
    public String getLoggedReps() { return dayActivityRootEntity.dayGymActivityInfo.loggedReps; }

    public String getUserName() { return dayActivityRootEntity.userName; }

    public boolean isAcceptable() {
        return dayActivityRootEntity.isAcceptable();
    }

//    public int calculateScoringPoints() {
//        return agencyResultRootEntity.calculateScoringPoints();
//    }

    //    @AggregateBuilder
    public static class DayActivityBuilder {
        private long id;

        private String amountFactor;

        private Boolean isLogged;

        private LocalDate logDate;

        private DayActivityRecurringInfo dayActivityRecurringInfo;

        private DayGymActivityInfo dayGymActivityInfo;

        private String activityId;

        private String activityTypeId;


        private String userName;

        private String activityTypeTitle;


        public DayActivityBuilder() {
//            this.koCriteria = new HashSet<KoCriteria>();
//            this.warningMessages = new HashSet<WarningMessage>();
        }

        public DayActivityBuilder withId(long id) {
            if (this.id < 0) {
                throw new IllegalArgumentException("wrong id");
            }
            this.id = id;
            return this;
        }

        public DayActivityBuilder withAmountFactor(String amountFactor) {
            this.amountFactor = amountFactor;
            return this;
        }

        public DayActivityBuilder withIsLogged(Boolean isLogged) {
            this.isLogged = isLogged;
            return this;
        }

        public DayActivityBuilder withLogDate(LocalDate logDate) {
            this.logDate = logDate;
            return this;
        }

        public DayActivityBuilder withActivityId(String activityId) {
            this.activityId = activityId;
            return this;
        }

        public DayActivityBuilder withActivityTypeId(String activityTypeId) {
            this.activityTypeId = activityTypeId;
            return this;
        }

        public DayActivityBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public DayActivityBuilder withActivityTypeTitle(String activityTypeTitle) {
            this.activityTypeTitle = activityTypeTitle;
            return this;
        }

        public DayActivityBuilder withDayActivityRecurringInfo(String recurEvery, String recurPeriod) {
            // todo validation
            this.dayActivityRecurringInfo = new DayActivityRecurringInfo(Integer.parseInt(recurEvery), Integer.parseInt(recurPeriod));
            return this;
        }

        public DayActivityBuilder withDayGymActivityInfo(String loggedReps, String todoKg, String todoSets, String todoReps) {
            // todo validation
            this.dayGymActivityInfo = new DayGymActivityInfo(loggedReps, todoKg, todoSets, todoReps);
            return this;
        }

        public DayActivityAggregate build() {
//            if (this.points <= 0) {
//                throw new IllegalStateException("Please set points > 0 with the withPoints mehtod");
//            }
//            if (this.personId == null) {
//                throw new IllegalStateException("Please set a person with the forPerson method");
//            }
            return new DayActivityAggregate(this);
        }

    }

}

