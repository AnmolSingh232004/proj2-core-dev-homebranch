package org.tasos.proj2.applicationlogic.services.implementation;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tasos.proj2.applicationservices.services.ActivityTypeServiceI;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;

public class ActivityTypeService implements ActivityTypeServiceI {

    private final ActivityTypeRepositoryI activityTypeRepository;

    @Inject
    public ActivityTypeService(ActivityTypeRepositoryI activityTypeRepository) {
        this.activityTypeRepository = activityTypeRepository;
    }

    @Override
    @Transactional
    public String createActivityTypeForUser(String newActType, String userName) {
        ActivityType newActivityType = activityTypeRepository.saveNewActivityType(newActType, userName);
        return newActivityType.getTitle();
    }

    @Override
    @Transactional
    public ActivityType createFullActivityTypeForUser(String newActType, String userName) {
        ActivityType newActivityType = activityTypeRepository.saveNewActivityType(newActType, userName);
        return newActivityType;
    }

    @Override
    public List<ActivityType> getAllActivityTypesForUser(String userName) {
        List<ActivityType> allUserTypes = activityTypeRepository.findAllTypesForUser(userName);

        return allUserTypes;
    }

    @Override
    public void deleteActivityType(Long id) throws Exception {
        this.activityTypeRepository.deleteById(id);
    }

}
