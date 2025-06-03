package org.tasos.proj2.domain.activity;

import java.util.HashSet;
import java.util.Set;

//@Aggregate
public class ActivityAggregate {
    ActivityRootEntity activityRootEntity;

    protected ActivityRootEntity getActivityRootEntity() {
        return activityRootEntity;
    }

    public Long getId() {
        return activityRootEntity.getId();
    }
    public String getTitle() {
        return activityRootEntity.getTitle();
    }
    public String getActivitySubType() {
        return activityRootEntity.getActivitySubType().getTitle();
    }
    public Long getActivityTypeId() {
        return activityRootEntity.getActivityType().getId();
    }
    public String getActivityTypeTitle() {
        return activityRootEntity.getActivityType().getTitle();
    }
    public String getActivityTypeDescription() {
        return activityRootEntity.getActivityType().getDescription();
    }
    public String getUserName() {
        return activityRootEntity.getUserName();
    }


    private ActivityAggregate(ActivityBuilder builder) {
        this.activityRootEntity = new ActivityRootEntity(builder.id, builder.title);
        this.activityRootEntity.addActivitySubType(builder.activitySubType);
        this.activityRootEntity.addActivityType(builder.activityType);
        this.activityRootEntity.addUserName(builder.userName);
    }

    public boolean isAcceptable() {
        return activityRootEntity.isAcceptable();
    }

//    public int calculateScoringPoints() {
//        return agencyResultRootEntity.calculateScoringPoints();
//    }

    //    @AggregateBuilder
    public static class ActivityBuilder {
        private Long id;
        private String title;
        private ActivitySubType activitySubType;
        private ActivityType activityType;
//        private PersonId personId;

        private String userName;

        public ActivityBuilder() {
//            this.koCriteria = new HashSet<KoCriteria>();
//            this.warningMessages = new HashSet<WarningMessage>();
        }

        public ActivityBuilder withId(Long id) {
            if (id.longValue() < 0) {
                throw new IllegalArgumentException("wrong id");
            }
            this.id = id;
            return this;
        }

        public ActivityBuilder withTitle(String title) {
            if (title.equalsIgnoreCase("")) {
                throw new IllegalArgumentException("wrong title");
            }
            this.title = title;
            return this;
        }

        public ActivityBuilder withActivitySubType(String subType) {
            this.activitySubType = new ActivitySubType(subType);
            return this;
        }

        public ActivityBuilder withActivityType(Long id, String title, String desc) {
            this.activityType = new ActivityType(id, title, desc);
            return this;
        }

        public ActivityBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public ActivityAggregate build() {
//            if (this.points <= 0) {
//                throw new IllegalStateException("Please set points > 0 with the withPoints mehtod");
//            }
//            if (this.personId == null) {
//                throw new IllegalStateException("Please set a person with the forPerson method");
//            }
            return new ActivityAggregate(this);
        }

    }
}

