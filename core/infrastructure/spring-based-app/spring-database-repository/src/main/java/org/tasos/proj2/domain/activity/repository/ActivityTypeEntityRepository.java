package org.tasos.proj2.domain.activity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;

public interface ActivityTypeEntityRepository extends JpaRepository<ActivityTypeEntity, Long> {

    ActivityTypeEntity findByTitle(String title);

    List<ActivityTypeEntity> findByUser(String user);

    @Query("SELECT a FROM ActivityTypeEntity a WHERE a.user IS NULL OR a.user = ''")
    List<ActivityTypeEntity> findByUserIsNullOrEmpty();

}