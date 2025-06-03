package org.tasos.proj2.springdependencyinjection.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tasos.proj2.domain.activity.ActivityDomainToEntityMapper;
import org.tasos.proj2.domain.activity.ActivityTypeDomainToEntityMapper;
import org.tasos.proj2.domain.activity.repository.ActivityEntityRepository;
import org.tasos.proj2.domain.activity.repository.ActivityTypeEntityRepository;
import org.tasos.proj2.domain.activity.repository.impl.ActivityRepository;
import org.tasos.proj2.domain.activity.repository.impl.ActivityTypeRepository;
import org.tasos.proj2.domain.dayactivity.DayActivityDomainToEntityMapper;
import org.tasos.proj2.domain.dayactivity.repository.DayActivityEntityRepository;
import org.tasos.proj2.domain.dayactivity.repository.impl.DayActivityRepository;
import org.tasos.proj2.domain.globalsessionflags.GlobalSessionFlagsDomainToEntityMapper;
import org.tasos.proj2.domain.globalsessionflags.repository.GlobalFlagEntityRepository;
import org.tasos.proj2.domain.globalsessionflags.repository.SessionFlagDatesEntityRepository;
import org.tasos.proj2.domain.globalsessionflags.repository.impl.GlobalFlagRepository;
import org.tasos.proj2.domain.globalsessionflags.repository.impl.SessionFlagDatesRepository;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;
import org.tasos.proj2.repositoryinterface.dayactivity.DayActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.GlobalFlagRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.SessionFlagDatesRepositoryI;

@Configuration
public class RepositoryFactory {

    // Activity BC
    @Bean
    public ActivityRepositoryI activityRepository(ActivityDomainToEntityMapper activityDomainToEntityMapper, ActivityEntityRepository activityEntityRepository) {
        return new ActivityRepository(activityDomainToEntityMapper, activityEntityRepository);
    }

    @Bean
    public ActivityTypeRepositoryI activityTypeRepository(ActivityTypeDomainToEntityMapper activityTypeDomainToEntityMapper, ActivityTypeEntityRepository activityTypeEntityRepository) {
        return new ActivityTypeRepository(activityTypeDomainToEntityMapper, activityTypeEntityRepository);
    }

    // DayActivity BC
    @Bean
    public DayActivityRepositoryI dayActivityRepository(DayActivityDomainToEntityMapper dayActivityDomainToEntityMapper, DayActivityEntityRepository dayActivityEntityRepository) {
        return new DayActivityRepository(dayActivityDomainToEntityMapper, dayActivityEntityRepository);
    }

    // SessionFlagDates BC
    @Bean
    public SessionFlagDatesRepositoryI sessionFlagDatesRepository(GlobalSessionFlagsDomainToEntityMapper globalSessionFlagsDomainToEntityMapper, SessionFlagDatesEntityRepository sessionFlagDatesEntityRepository) {
        return new SessionFlagDatesRepository(globalSessionFlagsDomainToEntityMapper, sessionFlagDatesEntityRepository);
    }

    @Bean
    public GlobalFlagRepositoryI globalFlagRepositoryI(GlobalSessionFlagsDomainToEntityMapper globalSessionFlagsDomainToEntityMapper, GlobalFlagEntityRepository globalFlagEntityRepository) {
        return new GlobalFlagRepository(globalSessionFlagsDomainToEntityMapper, globalFlagEntityRepository);
    }

}
