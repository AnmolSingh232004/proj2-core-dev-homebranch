package org.tasos.proj2.springdependencyinjection.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tasos.proj2.applicationlogic.services.implementation.ActivityService;
import org.tasos.proj2.applicationlogic.services.implementation.ActivityTypeService;
import org.tasos.proj2.applicationlogic.services.implementation.DayActivityService;
import org.tasos.proj2.applicationlogic.services.implementation.SessionFlagDatesService;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.applicationservices.services.ActivityTypeServiceI;
import org.tasos.proj2.applicationservices.services.DayActivityServiceI;
import org.tasos.proj2.applicationservices.services.GlobalFlagServiceI;
import org.tasos.proj2.applicationservices.services.SessionFlagDatesServiceI;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.GlobalFlagRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.SessionFlagDatesRepositoryI;

@Configuration
public class ApplicationLogicFactory {

    @Bean
    public ActivityServiceI activityService(ActivityRepositoryI activityRepositoryI, ActivityTypeRepositoryI activityTypeRepositoryI) {
        return new ActivityService(activityRepositoryI, activityTypeRepositoryI);
    }

    @Bean
    public DayActivityServiceI dayActivityService(
            DayActivityRepositoryI dayActivityRepositoryI,
            ActivityRepositoryI activityRepositoryI,
            SessionFlagDatesRepositoryI sessionFlagDatesRepository,
            GlobalFlagRepositoryI globalFlagRepository
    ) {
        return new DayActivityService(dayActivityRepositoryI, activityRepositoryI, sessionFlagDatesRepository, globalFlagRepository);
    }

    @Bean
    public ActivityTypeServiceI activityTypeService(ActivityTypeRepositoryI activityTypeRepository) {
        return new ActivityTypeService(activityTypeRepository);
    }

    @Bean
    public SessionFlagDatesServiceI sessionFlagDatesService(
            SessionFlagDatesRepositoryI sessionFlagDatesRepository,
            GlobalFlagRepositoryI globalFlagRepository
    ) {
        return new SessionFlagDatesService(sessionFlagDatesRepository, globalFlagRepository);
    }

    @Bean
    public GlobalFlagServiceI sessionFlagDatesGlobalFlagService(
            SessionFlagDatesRepositoryI sessionFlagDatesRepository,
            GlobalFlagRepositoryI globalFlagRepository
    ) {
        return new SessionFlagDatesService(sessionFlagDatesRepository, globalFlagRepository);
    }

}
