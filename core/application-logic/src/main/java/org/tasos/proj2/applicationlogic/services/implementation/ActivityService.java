package org.tasos.proj2.applicationlogic.services.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.tasos.proj2.applicationlogic.services.implementation.util.ThrowingConsumer;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.activity.CreateActivityDTO;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;

public class ActivityService implements ActivityServiceI {

    private final ActivityRepositoryI activityRepository;

    private final ActivityTypeRepositoryI activityTypeRepository;

    @Inject
    public ActivityService(ActivityRepositoryI activityRepository,
                           ActivityTypeRepositoryI activityTypeRepository) {
        this.activityRepository = activityRepository;
        this.activityTypeRepository = activityTypeRepository;
    }


    @Override
    public ActivityAggregate createActivity(ActivityAggregate activity) throws Exception {
        AtomicReference<ActivityAggregate> aggr = new AtomicReference<>();
         Optional.ofNullable(activity.getId())
                .ifPresentOrElse(
                        (ThrowingConsumer<Long>) aps ->  {throw new Exception("A new activity cannot already have an ID");},
                        () -> { aggr.set(this.activityRepository.saveActivity(activity));});
         return aggr.get();
    }

    @Override
    public ActivityAggregate updateActivity(ActivityAggregate activity) throws Exception {
        if (activity.getId() == null) {
            throw new Exception("An existing activity cannot miss an ID");
        }
        return this.activityRepository.saveActivity(activity);
    }

    @Override
    public ActivityAggregate patchActivity(Long id, String title) throws Exception {
        return this.activityRepository.patchActivity(id, title);
    }

    @Override
    public List<ActivityAggregate> getAllActivitiesPerUser(String userName) throws Exception {
        return this.activityRepository.findAllByUserName(userName);
    }

    @Override
    public List<ActivityAggregate> getAllGymActivities() throws Exception {
        return this.activityRepository.findAllByActivitytype_Title("GYM");
    }

    @Override
    public List<ActivityAggregate> getAllGymActivitiesByUser(String userName) throws Exception {
        return this.activityRepository.findAllByActivitytype_TitleAndUserName("GYM", userName);
    }

    @Override
    public List<ActivityAggregate> getExpenseActivitiesByType(String activityType) throws Exception {
        return this.activityRepository.findAllByActivitytype_Title(activityType);
    }

    @Override
    public List<ActivityAggregate> getExpenseActivitiesByTypeAndUserName(String activityType, String userName) throws Exception {
        return this.activityRepository.findAllByActivitytype_TitleAndUserName(activityType, userName);
    }

    @Override
    public List<String> getSubTypesByActivityTypeAndUserName(String typeId, String userName) throws Exception {
        return new ArrayList<>(this.activityRepository.findAllByActivitytype_IdAndUserName(Long.parseLong(typeId), userName)
                .stream()
                .map(act -> act.getActivitySubType())
                .collect(Collectors.toSet()));
    }

    @Override
    public List<String> getSubTypesByActivityType(String typeId) throws Exception {
        return new ArrayList<>(this.activityRepository.findAllByActivitytype_Id(Long.parseLong(typeId))
          .stream()
          .map(act -> act.getActivitySubType())
          .collect(Collectors.toSet()));
    }

    @Override
    public Optional<ActivityAggregate> getActivity(Long id) throws Exception {
        return this.activityRepository.findById(id);
    }

    @Override
    public void deleteActivity(Long id) throws Exception {
        this.activityRepository.deleteById(id);
    }

    @Override
    public void createActivity(CreateActivityDTO createActivityDTO) throws Exception {
        ActivityAggregate activity = null;

        if (!createActivityDTO.getNewSubType().equalsIgnoreCase("")) {
            activity = new ActivityAggregate.ActivityBuilder()
                    .withId(Long.parseLong(createActivityDTO.getActTypeId()))
                    .withActivitySubType(createActivityDTO.getNewSubType())
                    .withTitle(createActivityDTO.getNewActName()).build();
        } else if (!createActivityDTO.getSelectedSubType().equalsIgnoreCase("")) {
            activity = new ActivityAggregate.ActivityBuilder()
                    .withId(Long.parseLong(createActivityDTO.getActTypeId()))
                    .withActivitySubType(createActivityDTO.getSelectedSubType())
                    .withTitle(createActivityDTO.getNewActName()).build();
        }
        activityRepository.saveActivity(activity);
    }

    @Override
    public ActivityType findByTitle(String title) throws Exception {
        return this.activityTypeRepository.findByTitle(title);
    }

}
