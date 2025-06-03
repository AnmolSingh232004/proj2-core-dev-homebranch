package org.tasos.proj2.springrestservices.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTypeResponse {

    private Long id;
    private String title;
    private String description;
}
