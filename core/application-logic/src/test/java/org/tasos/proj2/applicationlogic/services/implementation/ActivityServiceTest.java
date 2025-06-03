package org.tasos.proj2.applicationlogic.services.implementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ActivityServiceTest {

    private ActivityService activityService;

    @Mock
    private ActivityRepositoryI activityRepositoryIMock;

    @BeforeEach
    public void setup() {
        activityService = new ActivityService(activityRepositoryIMock, null);
    }

    @DisplayName("JUnit test: verify ActivitySubTypes Of an ActType")
    @Test
    public void verifyActivitySubTypesOfActType() throws Exception {
        System.out.println("verifyActivitySubTypesOfActType started");

        List<ActivityAggregate> inputList = getActivities();

        // when
        when(activityRepositoryIMock.findAllByActivitytype_Id(anyLong()))
                .thenReturn(inputList);

        // execute
        List<String> subTypesOfActTypeList = activityService.getSubTypesByActivityType("1");

        // then
        assertThat(subTypesOfActTypeList, contains("actSubType2", "actSubType3", "actSubType1"));
        assertThat(subTypesOfActTypeList.size(), equalTo(3) );
    }

    private List getActivities() {
        List<ActivityAggregate> activities = new ArrayList<>();
        ActivityAggregate aggr1 = new ActivityAggregate.ActivityBuilder()
                .withId(1L)
                .withTitle("activity1")
                .withActivitySubType("actSubType1")
                .withActivityType(1L,"","")
                .build();
        ActivityAggregate aggr2 = new ActivityAggregate.ActivityBuilder()
                .withId(2L)
                .withTitle("activity2")
                .withActivitySubType("actSubType2")
                .withActivityType(1L,"","")
                .build();
        ActivityAggregate aggr3 = new ActivityAggregate.ActivityBuilder()
                .withId(3L)
                .withTitle("activity3")
                .withActivitySubType("actSubType2")
                .withActivityType(1L,"","")
                .build();
        ActivityAggregate aggr4 = new ActivityAggregate.ActivityBuilder()
                .withId(4L)
                .withTitle("activity4")
                .withActivitySubType("actSubType1")
                .withActivityType(1L,"","")
                .build();
        ActivityAggregate aggr5 = new ActivityAggregate.ActivityBuilder()
                .withId(5L)
                .withTitle("activity5")
                .withActivitySubType("actSubType3")
                .withActivityType(1L,"","")
                .build();
        activities.add(aggr1);
        activities.add(aggr2);
        activities.add(aggr3);
        activities.add(aggr4);
        activities.add(aggr5);
        return activities;
    }
}
