package org.tasos.proj2.springrestservices.dto.globalFlag;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GlobalFlagResponse {

    private Long id;
    private String name;
    private String description;
    private String isActive;
    private String userName;
}