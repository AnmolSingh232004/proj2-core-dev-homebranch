package org.tasos.proj2.springrestservices.dto.dayactivity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class DeleteDayActivityRequest {

    private Long activityId;
    private LocalDate logDate;
    private String username;

}
