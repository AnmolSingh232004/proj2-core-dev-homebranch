package org.tasos.proj2.springrestservices.dto.activity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateActivityRequest {

    private Long id;
    private String title;
    private String activitySubType;
    private ActivityTypeRequest activitytype;
    private String userName;

    @Setter
    @Getter
    public class ActivityTypeRequest {

        private Long id;
        private String title;
        private String description;
    }

}
