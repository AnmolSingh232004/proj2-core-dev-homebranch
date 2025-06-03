package org.tasos.proj2.repositoryinterface.globalsessionflags;

import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;

import java.util.List;
import java.util.Optional;

public interface GlobalFlagRepositoryI {

    GlobalFlagDomain findByNameEquals(String name);

    GlobalFlagDomain findByNameEqualsAndUserName(String name, String userName);

    GlobalFlagDomain save(GlobalFlagDomain globalFlag);

    List<GlobalFlagDomain> findAllByUserName(String userName);

    GlobalFlagDomain findById(Long id) throws Exception;
}
