package org.tasos.proj2.applicationservices.services;

import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;

import java.net.URISyntaxException;
import java.util.List;

public interface GlobalFlagServiceI {

    GlobalFlagDomain createGlobalFlag(GlobalFlagDomain globalFlag) throws URISyntaxException;

    GlobalFlagDomain updateGlobalFlag(GlobalFlagDomain globalFlag);

    List<GlobalFlagDomain> getAllGlobalFlagsPerUser(String userName);

    GlobalFlagDomain getGlobalFlag(Long id) throws Exception;
}
