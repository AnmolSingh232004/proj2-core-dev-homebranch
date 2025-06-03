package org.tasos.proj2.domain.globalsessionflags.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tasos.proj2.domain.globalsessionflags.model.GlobalFlagEntity;

import java.util.List;
import java.util.Optional;

public interface GlobalFlagEntityRepository extends JpaRepository<GlobalFlagEntity, Long> {

    GlobalFlagEntity findByNameEquals(String name);

    GlobalFlagEntity findByNameEqualsAndUserName(String name, String userName);

    Optional<GlobalFlagEntity> findById(Long id);

    List<GlobalFlagEntity> findAllByUserName(String userName);
}
