package org.tasos.proj2.springrestservices.dto.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class NewActivityRequest {

//    private Long id;
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
