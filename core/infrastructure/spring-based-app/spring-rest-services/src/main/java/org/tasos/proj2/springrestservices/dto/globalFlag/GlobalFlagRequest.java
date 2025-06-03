package org.tasos.proj2.springrestservices.dto.globalFlag;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalFlagRequest {

    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private String userName;
}