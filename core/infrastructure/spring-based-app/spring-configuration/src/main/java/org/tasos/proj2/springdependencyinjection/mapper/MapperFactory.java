package org.tasos.proj2.springdependencyinjection.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tasos.proj2.domain.activity.ActivityDomainToEntityMapper;
import org.tasos.proj2.domain.activity.ActivityTypeDomainToEntityMapper;
import org.tasos.proj2.domain.dayactivity.DayActivityDomainToEntityMapper;
import org.tasos.proj2.domain.globalsessionflags.GlobalSessionFlagsDomainToEntityMapper;
import org.tasos.proj2.springrestservices.mapper.ActivityDomainToResponseMapper;
import org.tasos.proj2.springrestservices.mapper.ActivityRequestsToDomainMapper;
import org.tasos.proj2.springrestservices.mapper.ActivityTypeDomainToResponseMapper;
import org.tasos.proj2.springrestservices.mapper.DayActivityDomainToResponseMapper;
import org.tasos.proj2.springrestservices.mapper.DayActivityRequestsToDomainMapper;
//import org.tasos.proj2.domain.activity.DomainToEntityMapper;

@Configuration
public class MapperFactory {

    // Activity BC / Activity Type BC
    @Bean
    public ActivityDomainToEntityMapper activityDomainToEntityMapper() {return new ActivityDomainToEntityMapper();}

    @Bean
    public ActivityTypeDomainToEntityMapper activityTypeDomainToEntityMapper() {return new ActivityTypeDomainToEntityMapper();}

    @Bean
    public ActivityDomainToResponseMapper activityDomainToResponseMapper() {
        return new ActivityDomainToResponseMapper();
    }

    @Bean
    public ActivityRequestsToDomainMapper activityRequestsToDomainMapper() {
        return new ActivityRequestsToDomainMapper();
    }

    @Bean
    public ActivityTypeDomainToResponseMapper activityTypeDomainToResponseMapper() {
        return new ActivityTypeDomainToResponseMapper();
    }


    // DayActivity BC
    @Bean
    public DayActivityDomainToEntityMapper dayActivityDomainToEntityMapper() {return new DayActivityDomainToEntityMapper();}

    @Bean
    public DayActivityDomainToResponseMapper dayActivityDomainToResponseMapper() {return new DayActivityDomainToResponseMapper();}

    @Bean
    public DayActivityRequestsToDomainMapper dayActivityRequestsToDomainMapper() {return new DayActivityRequestsToDomainMapper();}


    // SessionFlagDates BC
    @Bean
    public GlobalSessionFlagsDomainToEntityMapper gobalSessionFlagsDomainToEntityMapper() {return new GlobalSessionFlagsDomainToEntityMapper();}

}