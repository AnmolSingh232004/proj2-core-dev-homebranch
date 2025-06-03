package org.tasos.proj2.applicationservices.services;

import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;
import org.tasos.proj2.domain.dayactivity.DayActivityProjection;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;

import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SessionFlagDatesServiceI {

    SessionFlagDatesDomain endGymSession(SessionFlagDatesDomain sessionFlagDatesDTO);

    SessionFlagDatesDomain startGymSession(SessionFlagDatesDomain sessionFlagDatesDTO);

    SessionFlagDatesDomain getLastEndDateOfGymSessions(String userName);

}
