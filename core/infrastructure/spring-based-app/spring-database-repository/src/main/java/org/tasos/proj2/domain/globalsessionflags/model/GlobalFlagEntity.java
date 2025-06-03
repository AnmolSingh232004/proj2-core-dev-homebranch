package org.tasos.proj2.domain.globalsessionflags.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "global_flag")
public class GlobalFlagEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public GlobalFlagEntity(Long id, String name, String description, Boolean isActive, String userName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.userName = userName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "globalFlag")
    private Set<SessionFlagDatesEntity> sessionFlagDates = new HashSet<>();

    @Column(name = "user")
    private String userName;
}
