package org.tasos.proj2.springrestservices.controller.util.dayActivitiesAccumulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.springrestservices.controller.DayActivityController;
//import org.tasos.proj2.springrestservices.controller.util.auth.JWTUtils;
import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;
import org.tasos.proj2.springrestservices.dto.activity.ActivityTypeResponse;
import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivitiesPerTypeExtended;
import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivityInfo2;
import org.tasos.proj2.springrestservices.dto.dayactivity.DayActivityResponse2;
import org.tasos.proj2.springrestservices.mapper.ActivityDomainToResponseMapper;

@Component
public class DayActivitiesPerTypeAccumulator implements Accumulator<DayActivitiesPerTypeExtended> {
    private List<DayActivitiesPerTypeExtended> dayActivitiesPerTypeExtArray;

    private final ActivityServiceI activityService;

    private final ActivityDomainToResponseMapper activityDomainToResponseMapper;

    private final Logger logger = LoggerFactory.getLogger(DayActivitiesPerTypeAccumulator.class);

    public DayActivitiesPerTypeAccumulator(ActivityServiceI activityService, ActivityDomainToResponseMapper activityDomainToResponseMapper) {

        this.dayActivitiesPerTypeExtArray = new ArrayList<>();
        this.activityService = activityService;
        this.activityDomainToResponseMapper = activityDomainToResponseMapper;
    }

    @Override
    public List<DayActivitiesPerTypeExtended> getTotals(String key) {
        return dayActivitiesPerTypeExtArray;
    }

    @Override
    public void accumulate(DayActivityResponse2 incoming) {

        for (DayActivitiesPerTypeExtended existingDapte : dayActivitiesPerTypeExtArray) {
            if (existingDapte.getCategory().equalsIgnoreCase(incoming.getActivityTypeTitle())) {
                // Get existing dayActivities for type, add new incoming dayActivity, update list
                List<DayActivityInfo2> dayActs = existingDapte.getDayActivities();
                dayActs.add(map(incoming));
                existingDapte.setDayActivities(dayActs);

                // Check activity of incoming dayAct. If activityId found in existing dapte activities, do nothing, else add.
//                if (existingDapte.getActivities().stream().noneMatch(activity -> activity.getId().equals(Long.parseLong(incoming.getActivityId())))) {
//                    ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse(Long.parseLong(incoming.getActivityTypeId()), incoming.getActivityTypeTitle(), "");
//                    ActivityResponse actResp = new ActivityResponse(Long.parseLong(incoming.getActivityId()), incoming.getActivityTitle(), incoming.getActivitySubType(), activityTypeResponse);
//                    // add to existing dapte activities
//                    List<ActivityResponse> acts = existingDapte.getActivities();
//                    acts.add(actResp);
//                    existingDapte.setActivities(acts);
//                }

                // If we reach here, accumulate method is exited
                return;
            }
        }
        dayActivitiesPerTypeExtArray.add(toDayActivitiesPerTypeExt(incoming));
    }

    private DayActivityInfo2 map (DayActivityResponse2 resp2) {
        DayActivityInfo2 dai2 = new DayActivityInfo2();
        dai2.setId(resp2.getId());
        dai2.setActivityId(resp2.getActivityId());
        dai2.setActivityTitle(resp2.getActivityTitle());
        dai2.setActivityTypeId(resp2.getActivityTypeId());
        dai2.setTodoKg(resp2.getTodoKg());
        dai2.setTodoSets(resp2.getTodoSets());
        dai2.setTodoReps(resp2.getTodoReps());
        dai2.setLoggedReps(resp2.getLoggedReps());
        dai2.setAmountFactor(resp2.getAmountFactor());
        dai2.setLogDate(resp2.getLogDate());
        dai2.setActivityTypeTitle(resp2.getActivityTypeTitle());
        dai2.setActivitySubType(resp2.getActivitySubType());
        dai2.setIsLoggedd(resp2.getIsLoggedd());

        return dai2;
    }

    private DayActivitiesPerTypeExtended toDayActivitiesPerTypeExt(DayActivityResponse2 incoming)  {
        DayActivitiesPerTypeExtended dapte = new DayActivitiesPerTypeExtended();
        dapte.setDayActivities(new ArrayList<>(Arrays.asList(map(incoming))));
//        ActivityTypeResponse activityTypeResponse = new ActivityTypeResponse(Long.parseLong(incoming.getActivityTypeId()), incoming.getActivityTypeTitle(), "");
//        ActivityResponse actResp = new ActivityResponse(Long.parseLong(incoming.getActivityId()), incoming.getActivityTitle(), incoming.getActivitySubType(), activityTypeResponse);

        String userName = "user";
        try {
            List<ActivityResponse> expActivities = activityService.getExpenseActivitiesByTypeAndUserName(incoming.getActivityTypeTitle(), userName)
              .stream()
              .map(act -> this.activityDomainToResponseMapper.mapToActivityResponse(act))
              .collect(Collectors.toList());

            dapte.setActivities(expActivities);
        } catch (Exception e) {
            logger.debug("Error during retrieving activities for activity type: " + incoming.getActivityTypeTitle() + ", for user: "+ userName);
        }
        dapte.setCategory(incoming.getActivityTypeTitle());
        dapte.setCategoryId(incoming.getActivityTypeId());

        return dapte;
    }
}


//public class StatisticsAnalysisPerActivityAccumulator implements Accumulator<StatisticsPerActivityDTO> {
//    private List<StatisticsPerActivityDTO> activities;
//    private final ActivityRepositoryI activityRepository;
//
//    public StatisticsAnalysisPerActivityAccumulator(ActivityRepositoryI activityRepository) {
//        this.activities = new ArrayList<>();
//        this.activityRepository = activityRepository;
//    }
//
//    @Override
//    public List<StatisticsPerActivityDTO> getTotals(String key) {
//        return activities;
//    }
//
//    @Override
//    public void accumulate(DayActivityDTO t) {
//
//        for (StatisticsPerActivityDTO a : activities) {
//            if (Integer.parseInt(a.getActivityId()) == Integer.parseInt(t.getActivityId())) {
//                // Get existing list, add t, update list
//                List<DayActivityDTO> existingList = a.getDayActivities();
//                existingList.add(t);
//                a.setDayActivities(existingList);
//                // If we reach here, accumulate method is exited
//                return;
//            }
//        }
//        activities.add(toAnalysisPerActivity(t, t.getActivityId(), getActivityName(t.getActivityId())));
//    }
//
//    //	@Override
//    //	public String key(AccountTransactionDTO t) {
//    //		return "" + t.getMonth();
//    //	}
//    //
//    //	@Override
//    //	public String childKey(AccountTransactionDTO t) {
//    //		return "not_used";
//    //	}
//
//    private StatisticsPerActivityDTO toAnalysisPerActivity(DayActivityDTO t, String activityId, String activityName) {
//        StatisticsPerActivityDTO analysis = new StatisticsPerActivityDTO();
//        analysis.setActivityId(activityId);
//        analysis.setActivityName(activityName);
//        List<DayActivityDTO> dayActs = new ArrayList();
//        dayActs.add(t);
//        analysis.setDayActivities(dayActs);
//        return analysis;
//    }
//
//    public String getActivityName(String activityId) {
//        return this.activityRepository.findById(Long.parseLong(activityId)).get().getTitle();
//    }
//
//}
