package org.tasos.proj2.springrestservices.dto.sessionFlagDates;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SessionFlagDatesResponse {

    private GlobalFlagResponse globalFlagResponse;
    private LocalDate startDate;
    private LocalDate endDate;

    @Setter
    @Getter
    public class GlobalFlagResponse {

        private String name;
        private String description;
        private Boolean isActive;
    }

}