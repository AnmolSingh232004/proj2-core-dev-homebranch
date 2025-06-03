package org.tasos.proj2.domain.globalsessionflags.repository.impl;

import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.globalsessionflags.GlobalSessionFlagsDomainToEntityMapper;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.model.GlobalFlagEntity;
import org.tasos.proj2.domain.globalsessionflags.repository.GlobalFlagEntityRepository;
import org.tasos.proj2.repositoryinterface.globalsessionflags.GlobalFlagRepositoryI;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GlobalFlagRepository implements GlobalFlagRepositoryI {

    private final GlobalSessionFlagsDomainToEntityMapper globalSessionFlagsDomainToEntityMapper;

    private final GlobalFlagEntityRepository globalFlagEntityRepository;

    @Inject
    public GlobalFlagRepository(GlobalSessionFlagsDomainToEntityMapper globalSessionFlagsDomainToEntityMapper, GlobalFlagEntityRepository globalFlagEntityRepository) {
        this.globalSessionFlagsDomainToEntityMapper = globalSessionFlagsDomainToEntityMapper;
        this.globalFlagEntityRepository = globalFlagEntityRepository;
    }

    @Override
    public GlobalFlagDomain findByNameEquals(String name) {
        GlobalFlagEntity entity = globalFlagEntityRepository.findByNameEquals(name);
        if (entity != null) {
            return globalSessionFlagsDomainToEntityMapper.globalFlagEntityToGlobalFlag(entity);
        }
        return null;
    }

    @Override
    public GlobalFlagDomain findByNameEqualsAndUserName(String name, String userName) {
        GlobalFlagEntity entity = globalFlagEntityRepository.findByNameEqualsAndUserName(name, userName);
        if (entity != null) {
            return globalSessionFlagsDomainToEntityMapper.globalFlagEntityToGlobalFlag(entity);
        }
        return null;
    }

    @Override
    public GlobalFlagDomain save(GlobalFlagDomain globalFlag) {
        GlobalFlagEntity entity = globalFlagEntityRepository.save(globalSessionFlagsDomainToEntityMapper.globalFlagToGlobalFlagEntity(globalFlag));
        return globalSessionFlagsDomainToEntityMapper.globalFlagEntityToGlobalFlag(entity);
    }

    @Override
    public List<GlobalFlagDomain> findAllByUserName(String userName) {
        List<GlobalFlagEntity> globalFlags = globalFlagEntityRepository.findAllByUserName(userName);
        return globalFlags.stream().map(gf -> globalSessionFlagsDomainToEntityMapper.globalFlagEntityToGlobalFlag(gf)).collect(Collectors.toList());
    }

    @Override
    public GlobalFlagDomain findById(Long id) throws Exception {
        Optional<GlobalFlagEntity> globalFlagOpt = globalFlagEntityRepository.findById(id);
        GlobalFlagEntity globalFlagEntity = globalFlagOpt.orElseThrow(() -> new Exception("global flag id is not present"));
        return globalSessionFlagsDomainToEntityMapper.globalFlagEntityToGlobalFlag(globalFlagEntity);
    }
}
