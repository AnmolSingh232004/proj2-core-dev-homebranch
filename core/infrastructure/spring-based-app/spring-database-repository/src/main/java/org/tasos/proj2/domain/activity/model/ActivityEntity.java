package org.tasos.proj2.domain.activity.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tasos.proj2.domain.dayactivity.model.DayActivityEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "activity")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "activitySubType")
    private String activitySubType;

    @OneToMany(mappedBy = "activity",
            //cascade = CascadeType.ALL, --- Wont need to create Activity with DayActivities at the same time.
            orphanRemoval = true) // orphanRemoval: Delete all dayActivities when deleting one Activity
    private Set<DayActivityEntity> dayActivities = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("activities")
    private ActivityTypeEntity activitytype;

    @Column(name = "user")
    private String userName;

    public ActivityEntity(Long id, String title, String activitySubType, ActivityTypeEntity activitytype, String userName) {
        this.id = id;
        this.title = title;
        this.activitySubType = activitySubType;
        this.activitytype = activitytype;
        this.userName = userName;
    }

//    public ActivityEntity(String title, String activitySubType, ActivityTypeEntity activitytype) {
//        this.title = title;
//        this.activitySubType = activitySubType;
//        this.activitytype = activitytype;
//    }

    public ActivityEntity(Long id) {
        this.id = id;
    }

    // bidirectional consistency
//    public void addDayActivity(DayActivityEntity dayActivity) {
//        dayActivities.add(dayActivity);
//        dayActivity.setActivity(this);
//    }
//
//    public void removeDayActivity(DayActivityEntity dayActivity) {
//        dayActivities.remove(dayActivity);
//        dayActivity.setActivity(null);
//    }
}