package org.tasos.proj2.repositoryinterface.activity;

import org.tasos.proj2.domain.activity.ActivityAggregate;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Bartek <https://github.com/splaw88>
 */
public interface ActivityRepositoryI {

    ActivityAggregate findActivityById(long id);

    ActivityAggregate saveActivity(ActivityAggregate activity);

    ActivityAggregate updateActivity(ActivityAggregate activity);

    ActivityAggregate patchActivity(Long id, String title) throws Exception;

    ActivityAggregate deleteActivity(ActivityAggregate activity);

    List<ActivityAggregate> findAllByUserName(String userName);

// proj-2
    List<ActivityAggregate> findAllByActivitytype_Title(String actType);

    List<ActivityAggregate> findAllByActivitytype_TitleAndUserName(String actType, String userName);

    List<ActivityAggregate> findAllByActivitytype_Id(Long typeId);
    List<ActivityAggregate> findAllByActivitytype_IdAndUserName(Long typeId, String userName);
    Optional<ActivityAggregate> findById(Long activityId);

    void deleteById(Long id);

    List<ActivityAggregate> findAll();
}
