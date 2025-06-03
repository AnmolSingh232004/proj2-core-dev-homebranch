package org.tasos.proj2.domain.globalsessionflags.repository.impl;

import org.tasos.proj2.domain.globalsessionflags.GlobalSessionFlagsDomainToEntityMapper;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;
import org.tasos.proj2.domain.globalsessionflags.model.SessionFlagDatesEntity;
import org.tasos.proj2.domain.globalsessionflags.repository.SessionFlagDatesEntityRepository;
import org.tasos.proj2.repositoryinterface.globalsessionflags.SessionFlagDatesRepositoryI;

import javax.inject.Inject;

public class SessionFlagDatesRepository implements SessionFlagDatesRepositoryI {

    private final GlobalSessionFlagsDomainToEntityMapper globalSessionFlagsDomainToEntityMapper;

    private final SessionFlagDatesEntityRepository sessionFlagDatesEntityRepository;

    @Inject
    public SessionFlagDatesRepository(GlobalSessionFlagsDomainToEntityMapper globalSessionFlagsDomainToEntityMapper, SessionFlagDatesEntityRepository sessionFlagDatesEntityRepository) {
        this.globalSessionFlagsDomainToEntityMapper = globalSessionFlagsDomainToEntityMapper;
        this.sessionFlagDatesEntityRepository = sessionFlagDatesEntityRepository;
    }

    @Override
    public SessionFlagDatesDomain findFirstByEndDateIsNullAndGlobalFlag(GlobalFlagDomain globalFlag) {
        SessionFlagDatesEntity entity = sessionFlagDatesEntityRepository.findFirstByEndDateIsNullAndGlobalFlag(globalSessionFlagsDomainToEntityMapper.globalFlagToGlobalFlagEntity(globalFlag));
        if (entity != null) {
            return globalSessionFlagsDomainToEntityMapper.sessionFlagDatesEntityToSessionFlagDates(entity);
        }
        return null;
    }

    @Override
    public SessionFlagDatesDomain findFirstByGlobalFlagOrderByEndDateDesc(GlobalFlagDomain globalFlag) {
        SessionFlagDatesEntity entity = sessionFlagDatesEntityRepository.findFirstByGlobalFlagOrderByEndDateDesc(globalSessionFlagsDomainToEntityMapper.globalFlagToGlobalFlagEntity(globalFlag));
        if (entity != null) {
            return globalSessionFlagsDomainToEntityMapper.sessionFlagDatesEntityToSessionFlagDates(entity);
        }
        return null;
    }

    @Override
    public SessionFlagDatesDomain save(SessionFlagDatesDomain domainToSave) {
        SessionFlagDatesEntity entity = sessionFlagDatesEntityRepository.save(globalSessionFlagsDomainToEntityMapper.sessionFlagDatesToSessionFlagDatesEntity(domainToSave));
        if (entity != null) {
            return globalSessionFlagsDomainToEntityMapper.sessionFlagDatesEntityToSessionFlagDates(entity);
        }
        return null;
    }
}
