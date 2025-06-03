package org.tasos.proj2.domain.activity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tasos.proj2.domain.dayactivity.model.DayActivityEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "activity_type")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActivityTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public ActivityTypeEntity(Long id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.description = desc;
    }

    public ActivityTypeEntity(String title, String desc, String user) {
        this.title = title;
        this.description = desc;
        this.user = user;
    }

    public ActivityTypeEntity(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "user")
    private String user;

    @OneToMany(mappedBy = "activitytype", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<DayActivityEntity> dayActivities = new HashSet<>();

    @OneToMany(mappedBy = "activitytype", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ActivityEntity> activities = new HashSet<>();
}
