package org.tasos.proj2.springrestservices.dto.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private Long id;
    private String title;
    private String activitySubType;
    private ActivityTypeResponse activitytype;
}
