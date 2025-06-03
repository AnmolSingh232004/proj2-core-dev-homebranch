package org.tasos.proj2.domain.globalsessionflags;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SessionFlagDatesDomain {

    public SessionFlagDatesDomain(Long id, GlobalFlagDomain globalFlag, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.globalFlag = globalFlag;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Long id;

    private GlobalFlagDomain globalFlag;

    private LocalDate startDate;

    private LocalDate endDate;

}
