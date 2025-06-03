package org.tasos.proj2.domain.activity.repository.impl;

import org.springframework.transaction.annotation.Transactional;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityDomainToEntityMapper;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.activity.ActivityTypeDomainToEntityMapper;
import org.tasos.proj2.domain.activity.model.ActivityEntity;
import org.tasos.proj2.domain.activity.model.ActivityTypeEntity;
import org.tasos.proj2.domain.activity.repository.ActivityEntityRepository;
import org.tasos.proj2.domain.activity.repository.ActivityTypeEntityRepository;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ActivityTypeRepository implements ActivityTypeRepositoryI {

    private final ActivityTypeEntityRepository activityTypeEntityRepository;

    private final ActivityTypeDomainToEntityMapper activityTypeDomainToEntityMapper;

    @Inject
    public ActivityTypeRepository(ActivityTypeDomainToEntityMapper activityTypeDomainToEntityMapper,
                                  ActivityTypeEntityRepository activityTypeEntityRepository) {
        this.activityTypeEntityRepository = activityTypeEntityRepository;
        this.activityTypeDomainToEntityMapper = activityTypeDomainToEntityMapper;
    }

    @Override
    public ActivityType findByTitle(String title) {
        ActivityTypeEntity entity = activityTypeEntityRepository.findByTitle(title);
        return activityTypeDomainToEntityMapper.activityTypeEntityToActivityType(entity);
    }

    @Override
    public ActivityType saveNewActivityType(String type, String userName) {
        ActivityTypeEntity newType = new ActivityTypeEntity(type, "", userName);
        ActivityTypeEntity entity = activityTypeEntityRepository.save(newType);
        return activityTypeDomainToEntityMapper.activityTypeEntityToActivityType(entity);
    }

    @Override
    public List<ActivityType> findAllTypesForUser(String userName) {

        // Get default (free or paid) categories
        List<ActivityTypeEntity> entities1 = activityTypeEntityRepository.findByUserIsNullOrEmpty();

        List<ActivityTypeEntity> entities2 = activityTypeEntityRepository.findByUser(userName);

        entities1.addAll(entities2);

        return entities1.stream()
          .map(entity -> activityTypeDomainToEntityMapper.activityTypeEntityToActivityType(entity))
          .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        activityTypeEntityRepository.deleteById(id);
    }
}
