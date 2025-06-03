package org.tasos.proj2.domain.activity.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityDomainToEntityMapper;
import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.repository.ActivityEntityRepository;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ActivityRepository implements ActivityRepositoryI {

    private final ActivityDomainToEntityMapper activityDomainToEntityMapper;
    private final ActivityEntityRepository activityEntityRepository;

    @Inject
    public ActivityRepository(ActivityDomainToEntityMapper activityDomainToEntityMapper, ActivityEntityRepository activityEntityRepository) {
        this.activityDomainToEntityMapper = activityDomainToEntityMapper;
        this.activityEntityRepository = activityEntityRepository;
    }

    @Override
    public ActivityAggregate findActivityById(long id) {
        Optional<ActivityEntity> entity = activityEntityRepository.findById(id);
        if (entity.isPresent()) {
            return activityDomainToEntityMapper.activityEntityToActivity(entity.get());
        }
        return null;
    }

    @Override
    public List<ActivityAggregate> findAllByUserName(String userName) {
        List<ActivityEntity> activities = activityEntityRepository.findAllByUserName(userName);
        return activities.stream().map(act -> activityDomainToEntityMapper.activityEntityToActivity(act)).collect(Collectors.toList());
    }

    @Override
    public List<ActivityAggregate> findAllByActivitytype_Title(String actType) {
        List<ActivityEntity> activities = activityEntityRepository.findAllByActivitytype_Title(actType);
        return activities.stream().map(act -> activityDomainToEntityMapper.activityEntityToActivity(act)).collect(Collectors.toList());
    }

    @Override
    public List<ActivityAggregate> findAllByActivitytype_TitleAndUserName(String actType, String userName) {
        List<ActivityEntity> activities = activityEntityRepository.findAllByActivitytype_TitleAndUserName(actType, userName);
        return activities.stream().map(act -> activityDomainToEntityMapper.activityEntityToActivity(act)).collect(Collectors.toList());
    }

    @Override
    public List<ActivityAggregate> findAllByActivitytype_Id(Long id) {
        List<ActivityEntity> activities = activityEntityRepository.findAllByActivitytype_Id(id);
        return activities.stream().map(act -> activityDomainToEntityMapper.activityEntityToActivity(act)).collect(Collectors.toList());
    }

    @Override
    public List<ActivityAggregate> findAllByActivitytype_IdAndUserName(Long id, String userName) {
        List<ActivityEntity> activities = activityEntityRepository.findAllByActivitytype_IdAndUserName(id, userName);
        return activities.stream().map(act -> activityDomainToEntityMapper.activityEntityToActivity(act)).collect(Collectors.toList());
    }

    @Override
    public Optional<ActivityAggregate> findById(Long id) {
        Optional<ActivityEntity> activity = activityEntityRepository.findById(id);
        if (!activity.isPresent()) return null;
        ActivityAggregate aggr = activityDomainToEntityMapper.activityEntityToActivity(activity.get());
        return Optional.of(aggr);
    }

    @Override
    public ActivityAggregate saveActivity(ActivityAggregate activity) {
        return activityDomainToEntityMapper.activityEntityToActivity(activityEntityRepository.save(activityDomainToEntityMapper.activityToActivityEntity(activity)));
    }

    @Override
    public ActivityAggregate updateActivity(ActivityAggregate activity) {
        return saveActivity(activity);
    }

    @Override
    public ActivityAggregate patchActivity(Long id, String title) throws Exception {
        Optional<ActivityEntity> activity = activityEntityRepository.findById(id);
        ActivityEntity entity = activity.orElseThrow(() -> new Exception("Activity not found with id: " + id));
        entity.setTitle(title);
        activityEntityRepository.save(entity);
        return activityDomainToEntityMapper.activityEntityToActivity(entity);
    }


    @Override
    @Transactional
    public ActivityAggregate deleteActivity(ActivityAggregate activity) {
        activityEntityRepository.delete(activityDomainToEntityMapper.activityToActivityEntity(activity));
        return activity;
    }

    @Override
    public void deleteById(Long id) {
        activityEntityRepository.deleteById(id);
    }

}
