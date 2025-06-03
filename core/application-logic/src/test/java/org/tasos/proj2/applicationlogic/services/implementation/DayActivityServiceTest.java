package org.tasos.proj2.applicationlogic.services.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DayActivityServiceTest {

    @Mock
    private DayActivityRepositoryI dayActivityRepositoryIMock;

    @Mock
    private ActivityRepositoryI activityRepositoryIMock;

    @InjectMocks
    private DayActivityService dayActivityService;

    private DayActivityAggregate nextAggr;

    private DayActivityAggregate dayActivityAggregate;

    @BeforeEach
    public void setUp() {

        nextAggr = getNextDayAct();
    }

    @DisplayName("JUnit test: If user has achieved less reps, he must repeat same stuff in next session")
    @Test
    public void calculateNextExercisesTest1() {
        System.out.println("calculateNextExercisesTest1 started...");

        List<DayActivityAggregate> inputList = getDayActivities();
        DayActivityAggregate startAggr = inputList.get(0);

        // when
        when(activityRepositoryIMock.findById(anyLong()))
                .thenReturn(Optional.of(getActivityGymHypertrophy()));
        when(dayActivityRepositoryIMock.findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(anyLong(), any(LocalDate.class)))
                .thenReturn(Optional.of(nextAggr));

        // execute
        dayActivityService.calculateNextExercises(Stream.of(startAggr).collect(Collectors.toList()));

        // then
        assertThat(nextAggr.getTodoKg(), is(equalTo((getDayActivities().get(0)).getTodoKg())));
    }

    @DisplayName("JUnit test: If user has achieved more reps, xxxxxxx")
    @Test
    public void calculateNextExercisesTest_userExceededTodoReps() {

        // when
        when(activityRepositoryIMock.findById(anyLong()))
                .thenReturn(Optional.of(getActivityGymHypertrophy()));
        when(dayActivityRepositoryIMock.findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(anyLong(), any(LocalDate.class)))
                .thenReturn(Optional.of(nextAggr));

        // execute
        dayActivityService.calculateNextExercises(Stream.of(getValidGymDayActivity()).collect(Collectors.toList()));

        // then
        assertTrue(Long.parseLong(nextAggr.getTodoKg()) > Long.parseLong(getValidGymDayActivity().getTodoKg()), "Next exercise TodoKg is greater than current");
    }

    @DisplayName("JUnit test: calculateNextExercises should not execute for Expense")
    @Test
    public void calculateNextExercisesTest_dontExecuteForExpense() {

        // execute
        dayActivityService.calculateNextExercises(getVariousCarExpenseDayActivities().get());

        verify(dayActivityRepositoryIMock, never()).findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(anyLong(), any(LocalDate.class));
    }

    @DisplayName("JUnit test: calculateNextExercises should not execute for malformed Gym dayact")
    @Test
    public void calculateNextExercisesTest_dontExecuteForBadGym() {

        // execute
        dayActivityService.calculateNextExercises(Stream.of(getBadGymDayActivity()).collect(Collectors.toList()));

        verify(dayActivityRepositoryIMock, never()).findFirstByActivity_IdAndLogDateAfterOrderByLogDateAsc(anyLong(), any(LocalDate.class));
    }

    /**
     * Can check only last elements of each loop/dayActivity, due to ArgCaptor issue in for loops
     */
    @Test
    public void testGymDayActivitiesSavedAndGymRecurOK() {

        // execute
        dayActivityService.createDayActivitiesForDate(getVariousGymDayActivities());

        // then
        ArgumentCaptor<List<DayActivityAggregate>> myCaptor = ArgumentCaptor.forClass(List.class);
        verify(dayActivityRepositoryIMock, times(1)).save(myCaptor.capture());
        List<List<DayActivityAggregate>> eventsThatWereSentList = myCaptor.getAllValues();

        assertEquals(8, eventsThatWereSentList.get(0).size());
        // ArgCaptor only captures the 2 last elements for each loop/dayActivity
        // So, 1st -> actId=1, loggedReps=6
        // 2nd -> actId=2, loggedReps=7
        List<DayActivityAggregate> eventsThatWereSent = eventsThatWereSentList.get(0);

        assertThat(eventsThatWereSent.get(0).getTodoKg(), is(nullValue()));
        assertThat(eventsThatWereSent.get(0).getActivityId(), is(1L));
        assertThat(eventsThatWereSent.get(0).getLoggedReps(), is(nullValue()));
        assertThat(eventsThatWereSent.get(0).getLogDate(), is(LocalDate.of(2022,5,26)));

        assertThat(eventsThatWereSent.get(1).getTodoKg(), is(nullValue()));
        assertThat(eventsThatWereSent.get(1).getActivityId(), is(2L));
        assertThat(eventsThatWereSent.get(1).getLoggedReps(), is(nullValue()));
        assertThat(eventsThatWereSent.get(1).getLogDate(), is(LocalDate.of(2022,5,26)));

        assertThat(eventsThatWereSent.get(2).getTodoKg(), is(nullValue()));
        assertThat(eventsThatWereSent.get(2).getActivityId(), is(1L));
        assertThat(eventsThatWereSent.get(2).getLoggedReps(), is(nullValue()));
        assertThat(eventsThatWereSent.get(2).getLogDate(), is(LocalDate.of(2022,5,28)));

        assertThat(eventsThatWereSent.get(3).getTodoKg(), is(nullValue()));
        assertThat(eventsThatWereSent.get(3).getActivityId(), is(2L));
        assertThat(eventsThatWereSent.get(3).getLoggedReps(), is(nullValue()));
        assertThat(eventsThatWereSent.get(3).getLogDate(), is(LocalDate.of(2022,5,28)));

        assertThat(eventsThatWereSent.get(4).getTodoKg(), is(nullValue()));
        assertThat(eventsThatWereSent.get(4).getActivityId(), is(1L));
        assertThat(eventsThatWereSent.get(4).getLoggedReps(), is(nullValue()));
        assertThat(eventsThatWereSent.get(4).getLogDate(), is(LocalDate.of(2022,5,30)));

        assertThat(eventsThatWereSent.get(5).getTodoKg(), is(nullValue()));
        assertThat(eventsThatWereSent.get(5).getActivityId(), is(2L));
        assertThat(eventsThatWereSent.get(5).getLoggedReps(), is(nullValue()));
        assertThat(eventsThatWereSent.get(5).getLogDate(), is(LocalDate.of(2022,5,30)));

        assertThat(eventsThatWereSent.get(6).getTodoKg(), is("20"));
        assertThat(eventsThatWereSent.get(6).getActivityId(), is(1L));
        assertThat(eventsThatWereSent.get(6).getLoggedReps(), is("6"));
        assertThat(eventsThatWereSent.get(6).getLogDate(), is(LocalDate.of(2022,5,24)));

        assertThat(eventsThatWereSent.get(7).getTodoKg(), is("25"));
        assertThat(eventsThatWereSent.get(7).getActivityId(), is(2L));
        assertThat(eventsThatWereSent.get(7).getLoggedReps(), is("7"));
        assertThat(eventsThatWereSent.get(7).getLogDate(), is(LocalDate.of(2022,5,24)));
    }

    /**
     * Can check only last elements of each loop/dayActivity, due to ArgCaptor issue in for loops
     */
    @Test
    public void testGymDayActivitiesSaved_NoRecur() {

        // execute
        dayActivityService.createDayActivitiesForDate(getVariousGymDayActivitiesNoRecur());

        // then
        ArgumentCaptor<DayActivityAggregate> myCaptor = ArgumentCaptor.forClass(DayActivityAggregate.class);
        verify(dayActivityRepositoryIMock, times(2)).save(myCaptor.capture());
        List<DayActivityAggregate> eventsThatWereSent = myCaptor.getAllValues();

        assertEquals(2, eventsThatWereSent.size());

        assertThat(eventsThatWereSent.get(0).getTodoKg(), is("20"));
        assertThat(eventsThatWereSent.get(0).getLogDate(), is(LocalDate.of(2022, 5, 24)));
        assertThat(eventsThatWereSent.get(0).getActivityId(), is(1L));
        assertThat(eventsThatWereSent.get(0).getLoggedReps(), is("6"));

        assertThat(eventsThatWereSent.get(1).getTodoKg(), is("25"));
        assertThat(eventsThatWereSent.get(1).getLogDate(), is(LocalDate.of(2022, 5, 24)));
        assertThat(eventsThatWereSent.get(1).getActivityId(), is(2L));
        assertThat(eventsThatWereSent.get(1).getLoggedReps(), is("7"));
    }

    /**
     * Test 2-element list of same Expense type (car)
     */
    @Test
    public void testSameExpenseDayActivitiesSaved() {

        // execute
        dayActivityService.createDayActivitiesForDate(getVariousCarExpenseDayActivities());

        // then
        ArgumentCaptor<DayActivityAggregate> myCaptor = ArgumentCaptor.forClass(DayActivityAggregate.class);
        verify(dayActivityRepositoryIMock, times(2)).save(myCaptor.capture());
        List<DayActivityAggregate> eventsThatWereSent = myCaptor.getAllValues();

        assertEquals(2, eventsThatWereSent.size());

        assertThat(eventsThatWereSent.get(0).getAmountFactor(), is("250"));
        assertThat(eventsThatWereSent.get(0).getActivityId(), is(3L));
        assertThat(eventsThatWereSent.get(0).getLogDate(), is(LocalDate.of(2022, 6, 11)));

        assertThat(eventsThatWereSent.get(1).getAmountFactor(), is("47"));
        assertThat(eventsThatWereSent.get(1).getActivityId(), is(4L));
        assertThat(eventsThatWereSent.get(1).getLogDate(), is(LocalDate.of(2022, 6, 11)));
    }

    @Test
    public void testCloneDayActivitiesWithDates() {
        // when
        Optional<List<DayActivityAggregate>> dayActAggrs = getVariousGymDayActivitiesNoRecur();
        List<LocalDate> localDates = List.of(LocalDate.of(2022, 6, 12), LocalDate.of(2022, 6, 14));

        // execute
        List<DayActivityAggregate> ls = ReflectionTestUtils.invokeMethod(dayActivityService, "cloneDayActivitiesWithDates2", dayActAggrs.get(), localDates);

        // then
        ls.forEach(
                dayAct
                        -> {
                    if ((dayAct.getLogDate()).getDayOfMonth() == 12) {
                        assertThat(dayAct.getLogDate(), is(LocalDate.of(2022, 6, 12)));
                        assertThat(dayAct.getLogDate(), is(LocalDate.of(2022, 6, 12)));
                    } else {
                        assertThat(dayAct.getLogDate(), is(LocalDate.of(2022, 6, 14)));
                        assertThat(dayAct.getLogDate(), is(LocalDate.of(2022, 6, 14)));
                    }
                });
    }

    private List<DayActivityAggregate> getDayActivities() {
        List<DayActivityAggregate> dayActivities = new ArrayList<>();
        DayActivityAggregate aggr1 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(1))
                .withDayGymActivityInfo("6", "20", "3", "7")
                .withLogDate(LocalDate.of(2022, 5, 24))
                .build();
        dayActivities.add(aggr1);
        return dayActivities;
    }

    private DayActivityAggregate getNextDayAct() {
        return new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(1))
                .withDayGymActivityInfo("1", "1", "1", "1")
                .withLogDate(LocalDate.of(2022, 5, 28))
                .build();
    }

    private ActivityAggregate getActivityGymHypertrophy() {
        return new ActivityAggregate.ActivityBuilder()
                .withId(1L)
                .withActivitySubType("HYPERTROPHY")
                .build();
    }

    // Only Gym dayActs here, with only one of them having recurring info (Think of UI)
    private Optional<List<DayActivityAggregate>> getVariousGymDayActivities() {
        List<DayActivityAggregate> dayActivities = new ArrayList<>();
        // GYM 1
        DayActivityAggregate aggr1 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(1))
                .withActivityTypeId("1")
                .withDayActivityRecurringInfo("2", "1")
                .withDayGymActivityInfo("6", "20", "3", "7")
                .withLogDate(LocalDate.of(2022, 5, 24))
                .build();
        // GYM 2
        DayActivityAggregate aggr2 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(2))
                .withActivityTypeId("1")
                .withDayGymActivityInfo("7", "25", "3", "9")
                .withLogDate(LocalDate.of(2022, 5, 24))
                .build();
        dayActivities.add(aggr1);
        dayActivities.add(aggr2);
        return Optional.of(dayActivities);
    }

    // Only Gym dayActs here
    private Optional<List<DayActivityAggregate>> getVariousGymDayActivitiesNoRecur() {
        List<DayActivityAggregate> dayActivities = new ArrayList<>();
        // GYM 1
        DayActivityAggregate aggr1 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(1))
                .withActivityTypeId("1")
                .withDayGymActivityInfo("6", "20", "3", "7")
                .withLogDate(LocalDate.of(2022, 5, 24))
                .build();
        // GYM 2
        DayActivityAggregate aggr2 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(2))
                .withActivityTypeId("1")
                .withDayGymActivityInfo("7", "25", "3", "9")
                .withLogDate(LocalDate.of(2022, 5, 24))
                .build();
        dayActivities.add(aggr1);
        dayActivities.add(aggr2);
        return Optional.of(dayActivities);
    }

    private Optional<List<DayActivityAggregate>> getVariousCarExpenseDayActivities() {
        List<DayActivityAggregate> dayActivities = new ArrayList<>();
        // CAR 1
        DayActivityAggregate aggr1 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(3))
                .withAmountFactor("250")
                .withActivityTypeId("2")
                .withLogDate(LocalDate.of(2022, 6, 11))
                .build();
        // CAR 2
        DayActivityAggregate aggr2 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(4))
                .withAmountFactor("47")
                .withActivityTypeId("2")
                .withLogDate(LocalDate.of(2022, 6, 11))
                .build();
        dayActivities.add(aggr1);
        dayActivities.add(aggr2);
        return Optional.of(dayActivities);
    }

    private DayActivityAggregate getBadGymDayActivity() {
        // GYM
        DayActivityAggregate aggr1 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(1))
                .withDayActivityRecurringInfo("2", "1")
                .withDayGymActivityInfo("", "20", "3", "7")
                .withLogDate(LocalDate.of(2022, 5, 24))
                .build();
        return aggr1;
    }

    private DayActivityAggregate getValidGymDayActivity() {
        // GYM
        DayActivityAggregate aggr1 = new DayActivityAggregate.DayActivityBuilder()
                .withActivityId(String.valueOf(1))
//                .withDayActivityRecurringInfo("2","1")
                .withDayGymActivityInfo("11", "26", "3", "10")
                .withLogDate(LocalDate.of(2022, 4, 4))
                .build();
        return aggr1;
    }
}
