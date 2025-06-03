package org.tasos.proj2.repositoryinterface.globalsessionflags;

import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;

public interface SessionFlagDatesRepositoryI {

    SessionFlagDatesDomain findFirstByEndDateIsNullAndGlobalFlag(GlobalFlagDomain globalFlag);

    SessionFlagDatesDomain findFirstByGlobalFlagOrderByEndDateDesc(GlobalFlagDomain globalFlag);

    SessionFlagDatesDomain save(SessionFlagDatesDomain domain);
}
