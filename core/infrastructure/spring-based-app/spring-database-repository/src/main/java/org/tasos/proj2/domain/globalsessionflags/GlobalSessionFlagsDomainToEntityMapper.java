package org.tasos.proj2.domain.globalsessionflags;

import org.tasos.proj2.domain.globalsessionflags.model.GlobalFlagEntity;
import org.tasos.proj2.domain.globalsessionflags.model.SessionFlagDatesEntity;

public class GlobalSessionFlagsDomainToEntityMapper {

    // Global Flag BC
    public GlobalFlagDomain globalFlagEntityToGlobalFlag(GlobalFlagEntity entity) {
        return new GlobalFlagDomain(entity.getId(), entity.getName(), entity.getDescription(), entity.getIsActive(), entity.getUserName());
    }

    public GlobalFlagEntity globalFlagToGlobalFlagEntity(GlobalFlagDomain domain) {
        return new GlobalFlagEntity(domain.getId(), domain.getName(), domain.getDescription(), domain.getIsActive(), domain.getUserName());
    }


    // Session Flag Dates BC
    public SessionFlagDatesDomain sessionFlagDatesEntityToSessionFlagDates(SessionFlagDatesEntity entity) {
        return new SessionFlagDatesDomain(entity.getId(), globalFlagEntityToGlobalFlag(entity.getGlobalFlag()), entity.getStartDate(), entity.getEndDate());
    }

    public SessionFlagDatesEntity sessionFlagDatesToSessionFlagDatesEntity(SessionFlagDatesDomain domain) {
        return new SessionFlagDatesEntity(domain.getId(), globalFlagToGlobalFlagEntity(domain.getGlobalFlag()), domain.getStartDate(), domain.getEndDate());
    }
}
