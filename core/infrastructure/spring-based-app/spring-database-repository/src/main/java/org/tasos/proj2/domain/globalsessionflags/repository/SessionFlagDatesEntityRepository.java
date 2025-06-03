package org.tasos.proj2.domain.globalsessionflags.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tasos.proj2.domain.globalsessionflags.model.GlobalFlagEntity;
import org.tasos.proj2.domain.globalsessionflags.model.SessionFlagDatesEntity;

public interface SessionFlagDatesEntityRepository extends JpaRepository<SessionFlagDatesEntity, Long> {

    SessionFlagDatesEntity findFirstByEndDateIsNullAndGlobalFlag(GlobalFlagEntity globalFlag);

    SessionFlagDatesEntity findFirstByGlobalFlagOrderByEndDateDesc(GlobalFlagEntity globalFlag);

}
