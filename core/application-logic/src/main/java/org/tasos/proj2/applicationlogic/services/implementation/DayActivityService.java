package org.tasos.proj2.applicationlogic.services.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.tasos.proj2.applicationservices.services.DayActivityServiceI;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.domain.dayactivity.DayActivityRecurringInfo;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.GlobalFlagRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.SessionFlagDatesRepositoryI;

public class DayActivityService implements DayActivityServiceI {

    private final DayActivityRepositoryI dayActivityRepository;
    private final ActivityRepositoryI activityRepository;

    private final SessionFlagDatesRepositoryI sessionFlagDatesRepository;

    private final GlobalFlagRepositoryI globalFlagRepository;

    @Inject
    public DayActivityService(DayActivityRepositoryI dayActivityRepository, ActivityRepositoryI activityRepository,
                              SessionFlagDatesRepositoryI sessionFlagDatesRepository, GlobalFlagRepositoryI globalFlagRepository) {
        this.dayActivityRepository = dayActivityRepository;
        this.activityRepository = activityRepository;
        this.sessionFlagDatesRepository = sessionFlagDatesRepository;
        this.globalFlagRepository = globalFlagRepository;
    }

    @Override
    @Transactional
    public void deleteAllDayActivitiesByLogDateAndUserName(DayActivityAggregate dayActivity) {
        LocalDate logDateToDelete = dayActivity.getLogDate();
        String username = dayActivity.getUserName();
        List<DayActivityAggregate> dayActivities = dayActivityRepository.findAllByLogDateEqualsAndUserName(logDateToDelete, username);
        for (DayActivityAggregate dayActivityItem : dayActivities) {
//            Optional<DayActivity> optionalDayActivity = dayActivityRepository.findDayActivityByActivity_IdAndLogDateEquals(dayActivity.getId(), logDateToDelete);
//            if (optionalDayActivity.isPresent()) {
//                dayActivityRepository.delete(optionalDayActivity.get());
//            }
            dayActivityRepository.deleteDayActivityByActivity_IdAndLogDateEqualsAndUserName(dayActivityItem.getActivityId(), logDateToDelete, dayActivityItem.getUserName());
        }
    }


    /**
     *
     *
     This code is an override of the createDayActivitiesForDate() method, which takes an Optional<List<DayActivityAggregate>> as a parameter.

     It uses flatmap() to get rid of the Optional wrapper,

     and then checks if the DayActivityAggregate object has a RecurringInfo object with a non-zero RecurEvery and RecurPeriod.
     If so, it uses getDatesForStepAndPeriod() to generate a list of dates and uses cloneDayActivitiesWithDates2() to clone the DayActivityAggregate objects for each date.

     It then saves the original and cloned DayActivityAggregate objects in the dayActivityRepository.
     If the DayActivityAggregate object doesn't have a RecurringInfo object, it just saves the original DayActivityAggregate objects in the dayActivityRepository.
     * @param dayActivities
     * @return
     */
    @Override
    public List<DayActivityAggregate> createDayActivitiesForDate(Optional<List<DayActivityAggregate>> dayActivities) {

        Optional<List<DayActivityAggregate>> finalDayActivities = dayActivities;

        // 2nd way
        //        return dayActivities
        //                .filter(list -> !list.isEmpty())
        //                .map(list -> list.get(0))
        //                .filter(dayAct -> dayAct.getRecurringInfo() != null && dayAct.getRecurringInfo().getRecurEvery() != 0 && dayAct.getRecurringInfo().getRecurPeriod() != 0)
        //                .map(

        return dayActivities
                // The basic way is to use .map() with e.g. List<String>
                // We need .flatmap() gets rid of Optional<>, when using it with Optional.  e.g. Optional<List<String>>
                .flatMap(list -> list.stream().limit(1).filter(Objects::nonNull).findFirst())
                .filter(dayAct -> dayAct.getRecurringInfo() != null && dayAct.getRecurringInfo().getRecurEvery() != 0 && dayAct.getRecurringInfo().getRecurPeriod() != 0)
                // Case: has recurring info - so basically if Gym related
                .map(
                        dayAct -> {
                            DayActivityRecurringInfo recurringInfo = dayAct.getRecurringInfo();
                            // Gym - Recurring case
                            List<LocalDate> saveDates = getDatesForStepAndPeriod(dayAct.getLogDate(), recurringInfo.getRecurPeriod(), recurringInfo.getRecurEvery());
                            List<LocalDate> saveDatesNew = saveDates.subList(1, saveDates.size());
                            // Create clones of n dayActivities package, for each save date

                            List<DayActivityAggregate> finalDayActivities3 = cloneDayActivitiesWithDates2(dayActivities.get(), saveDatesNew);
                            List<DayActivityAggregate> origAndCloneDayActsList = new ArrayList<>();

                            origAndCloneDayActsList.addAll(finalDayActivities3);
                            origAndCloneDayActsList.addAll(dayActivities.get());

                            List<DayActivityAggregate> createdDayActs = dayActivityRepository.save(origAndCloneDayActsList);
                            return createdDayActs;
                        }
                )
                // Case: Without recurring info, normal
                .orElseGet(
                        () -> {
                            for (DayActivityAggregate dayActivity : finalDayActivities.get()) {
                                DayActivityAggregate result = dayActivityRepository.save(dayActivity);
                                // Gym - After saving, might update with to-do fields, depending on previous date's Logged info. PJ-454 (BUG-454-1)
                                //updateTodoFromPreviousLogged(dayActivity);
                            }
                            return dayActivities.get();
                        }
                );
    }

    @Override
    public void calculateNextExercises(List<DayActivityAggregate> dayActivities) {

        dayActivities
                .stream()
                .filter(gymDayActivityIsNotNull())
                .forEach(dto -> findNextGymIfAny_andCalcNext(dto));

    }

    private void findNextGymIfAny_andCalcNext(DayActivityAggregate dto) {
        dayActivityRepository.findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(dto.getActivityId(), dto.getLogDate()).ifPresent(nextDayActivity -> {
            calculateNextExercise(nextDayActivity, dto);
        });
    }

    private static Predicate<DayActivityAggregate> gymDayActivityIsNotNull() {
        return aggr -> aggr.getGymInfo() != null
                && aggr.getLoggedReps() != null && !aggr.getLoggedReps().equalsIgnoreCase("")
                && aggr.getTodoKg() != null && !aggr.getTodoKg().equalsIgnoreCase("")
                && aggr.getTodoReps() != null && !aggr.getTodoReps().equalsIgnoreCase("")
                && aggr.getTodoSets() != null && !aggr.getTodoSets().equalsIgnoreCase("");
    }

    /**
     * Only for current gym session
     *
     * @param dayActivity
     */
    public void updateTodoFromPreviousLogged(DayActivityAggregate dayActivity) {
        dayActivityRepository.findFirstByActivity_IdAndLogDateBeforeOrderByLogDateDesc(
                dayActivity.getActivityId(),
                dayActivity.getLogDate()
        ).ifPresent(previousDayActivity -> {
            // Check if date belongs to ongoing gym session
            GlobalFlagDomain gymGlobalFlag = globalFlagRepository.findByNameEquals("gymSession");
            SessionFlagDatesDomain sessionFlagDates = sessionFlagDatesRepository.findFirstByEndDateIsNullAndGlobalFlag(gymGlobalFlag);
            if (sessionFlagDates != null
                    && (previousDayActivity.getLogDate().isAfter(sessionFlagDates.getStartDate())
                    || previousDayActivity.getLogDate().isEqual(sessionFlagDates.getStartDate()))) {
                // If Logged information exists
                if (previousDayActivity.getLoggedReps() != null && !previousDayActivity.getLoggedReps().equalsIgnoreCase("")) {
                    calculateNextExercise(dayActivity, previousDayActivity);
                }
            }
        });
    }

    public List<DayActivityProjection> getDayActivitiesByDateAndUserName_WithExtraInfo(LocalDate date, String userName) {
        List<DayActivityProjection> dayActs = dayActivityRepository.findDayActivitiesByDateAndUserNameWithActivityTypeTitle(date, userName);
        return dayActs;
    }

    public List<DayActivityProjection> getDayActivitiesByDateAndUserName_WithExtraInfo_AndActivityTitle(LocalDate date, String userName) {
        List<DayActivityProjection> dayActs = dayActivityRepository.findDayActivitiesByDateAndUserNameWithActivityTypeTitleAndActTitle(date, userName);
        return dayActs;
    }

    public List<DayActivityAggregate> findAllByLogDateAndActivitytype(LocalDate date, String activityTypeId) {
        return dayActivityRepository.findAllByLogDateAndActivitytype(date, activityTypeId);
    }

    public List<DayActivityAggregate> findAllByActivitytypeAndUserName(ActivityType type, String userName) {
        return dayActivityRepository.findAllByActivitytypeAndUserName(String.valueOf(type.getId()), userName);
    }

    public List<DayActivityAggregate> findAllByLogDateAndUserName(LocalDate date, String userName) {
        return dayActivityRepository.findAllByLogDateEqualsAndUserName(date, userName);
    }

    @Override
    public List<DayActivityAggregate> findAllByUserNameWithActivityType(String userName) {
        return dayActivityRepository.findAllByUserNameWithActivityType(userName);
    }

    public Optional<DayActivityAggregate> findById(Long id) {
        return dayActivityRepository.findById(id);
    }

    /**
     * Calculate next gym activity (nextDayActivity) from a startingDto
     *
     * @param nextDayActivity
     * @param startingDto
     */
    private void calculateNextExercise2(DayActivityAggregate nextDayActivity, DayActivityAggregate startingDto) {
        // Strength or Hypertrophy sub type
        Optional<ActivityAggregate> exercise = activityRepository.findById(startingDto.getActivityId());
        if (!exercise.isPresent()) {
            return;
        }

        //todo use new switch
        if (exercise.get().getActivitySubType().equalsIgnoreCase("HYPERTROPHY")) {
            handleGymSubTypeCase(nextDayActivity, startingDto, 8, 10);

        } else if (exercise.get().getActivitySubType().equalsIgnoreCase("STRENGTH")) {
            handleGymSubTypeCase(nextDayActivity, startingDto, 3, 5);
        }
    }

    private void calculateNextExercise(DayActivityAggregate nextDayActivity, DayActivityAggregate startingDto) {
        // Strength or Hypertrophy sub type
        activityRepository.findById(startingDto.getActivityId()).ifPresentOrElse(
                (exercise)
                        -> {
                    //todo use new switch
                    if (exercise.getActivitySubType().equalsIgnoreCase("HYPERTROPHY")) {
                        handleGymSubTypeCase(nextDayActivity, startingDto, 8, 10);

                    } else if (exercise.getActivitySubType().equalsIgnoreCase("STRENGTH")) {
                        handleGymSubTypeCase(nextDayActivity, startingDto, 3, 5);
                    }
                },
                ()
                        -> {
                    System.out.println(
                            "do nothing..");
                });
    }

    /**
     * Handles Strengh and Hypertrophy gym subtypes
     *
     * @param nextDayActivity
     * @param dto
     * @param minReps
     * @param maxReps
     */
    private void handleGymSubTypeCase(DayActivityAggregate nextDayActivity, DayActivityAggregate dto, Integer minReps, Integer maxReps) {
        if (Integer.parseInt(dto.getLoggedReps()) == 0) return;
        if (Integer.parseInt(dto.getLoggedReps()) < Integer.parseInt(dto.getTodoReps())) {
            // Logged Reps < Todos Reps
            // Copy same todos info
            nextDayActivity.setTodoKg(dto.getTodoKg());
            nextDayActivity.setTodoSets(dto.getTodoSets());
            nextDayActivity.setTodoReps(dto.getTodoReps());
            dayActivityRepository.save(nextDayActivity);
        } else if (Integer.parseInt(dto.getLoggedReps()) >= Integer.parseInt(dto.getTodoReps())
                && Integer.parseInt(dto.getLoggedReps()) + 1 < maxReps + 1) {
            // If Logged Reps = Todos Reps, AND loggedReps+1 < Overflow limit (11)
            // Increase only todoReps by loggedReps + 1
            nextDayActivity.setTodoKg(dto.getTodoKg());
            nextDayActivity.setTodoSets(dto.getTodoSets());
            nextDayActivity.setTodoReps(Integer.toString(Integer.parseInt(dto.getLoggedReps()) + 1));
            dayActivityRepository.save(nextDayActivity);

        } else if (Integer.parseInt(dto.getLoggedReps()) >= Integer.parseInt(dto.getTodoReps())
                && Integer.parseInt(dto.getLoggedReps()) + 1 >= maxReps + 1) {
            // If Logged Reps = Todos Reps, AND todoReps = Overflow limit (6)
            // Increase todoKg and reset todoReps
            Double todoKgChange = Double.parseDouble(dto.getTodoKg());
            todoKgChange = todoKgChange + todoKgChange * 0.1;

            nextDayActivity.setTodoKg(Long.toString(Math.round(todoKgChange)));
            nextDayActivity.setTodoSets(dto.getTodoSets());
            nextDayActivity.setTodoReps(minReps + "");
            dayActivityRepository.save(nextDayActivity);
        }
    }

    private List<LocalDate> getDatesForStepAndPeriod(LocalDate start, Integer weekPeriod, Integer step) {
        // End date
        LocalDate end = start.plusDays(weekPeriod * 7);
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(step);
        }
        return totalDates;
    }

    /**
     * Make clones of dayActivities with recurring dates
     * GYM related
     * User creates the new session: Adds multiple gym activities, and also recurring info
     * We want to clone all gym activities for each saveDate input
     *
     * @param dayActivityDtos
     * @param saveDates
     * @return
     */
    private List<DayActivityAggregate> cloneDayActivitiesWithDates2(List<DayActivityAggregate> dayActivityDtos, List<LocalDate> saveDates) {

        List<DayActivityAggregate> allClones = new ArrayList<>();

        for (LocalDate date : saveDates) {
            dayActivityDtos
                    .forEach(dayAct -> {
                        DayActivityAggregate dayActClone = new DayActivityAggregate.DayActivityBuilder()
                                .withActivityId(String.valueOf(dayAct.getActivityId()))
                                .withIsLogged(false)
                                .withActivityTypeId(String.valueOf(dayAct.getActivityTypeId()))
                                .withDayGymActivityInfo(null, null, null, null)
                                .withLogDate(date)
                                .withUserName(dayAct.getUserName())
                                .build();
                        allClones.add(dayActClone);
                    });
        }
        return allClones;
    }
}
