package org.tasos.proj2.domain.activity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tasos.proj2.domain.activity.model.ActivityEntity;

import java.util.List;
import java.util.Optional;

public interface ActivityEntityRepository extends JpaRepository<ActivityEntity, Long> {

    List<ActivityEntity> findAllByActivitytype_Title(String actType);

    List<ActivityEntity> findAllByActivitytype_TitleAndUserName(String actType, String userName);

    List<ActivityEntity> findAllByActivitytype_IdAndUserName(Long typeId, String userName);

    List<ActivityEntity> findAllByActivitytype_Id(Long typeId);


    Optional<ActivityEntity> findById(Long activityId);

    List<ActivityEntity> findAllByUserName(String userName);



}