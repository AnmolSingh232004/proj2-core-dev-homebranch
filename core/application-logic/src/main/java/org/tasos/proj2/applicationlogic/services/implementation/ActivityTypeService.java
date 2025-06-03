package org.tasos.proj2.applicationlogic.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tasos.proj2.applicationservices.services.ActivityTypeServiceI;
import org.tasos.proj2.applicationservices.services.DayActivityServiceI;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.domain.dayactivity.DayActivityRecurringInfo;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.GlobalFlagRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.SessionFlagDatesRepositoryI;

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

//        return allUserTypes.stream()
//          .map(actType -> actType.getTitle())
//          .collect(Collectors.toList());
    }

    @Override
    public void deleteActivityType(Long id) throws Exception {
        this.activityTypeRepository.deleteById(id);
    }

}
