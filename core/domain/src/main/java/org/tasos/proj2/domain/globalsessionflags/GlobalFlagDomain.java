package org.tasos.proj2.domain.globalsessionflags;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlobalFlagDomain {

    public GlobalFlagDomain(Long id, String name, String description, Boolean isActive, String userName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.userName = userName;
    }

    // used by eventbus
    public GlobalFlagDomain(String name, Boolean isActive, String userName) {
        this.name = name;
        this.isActive = isActive;
        this.userName = userName;
    }

    public GlobalFlagDomain(String userName) {
        this.userName = userName;
    }

    public Long id;

    private String name;

    private String description;

    private Boolean isActive;

    private String userName;
}
