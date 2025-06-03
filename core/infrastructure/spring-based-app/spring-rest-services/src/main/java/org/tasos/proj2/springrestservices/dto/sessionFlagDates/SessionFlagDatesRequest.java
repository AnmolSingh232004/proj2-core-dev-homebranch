package org.tasos.proj2.springrestservices.dto.sessionFlagDates;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SessionFlagDatesRequest {

    private GlobalFlagRequest globalFlagRequest;
    private LocalDate startDate;
    private LocalDate endDate;

    @Setter
    @Getter
    public class GlobalFlagRequest {

        private String name;
        private String description;
        private Boolean isActive;
    }

}