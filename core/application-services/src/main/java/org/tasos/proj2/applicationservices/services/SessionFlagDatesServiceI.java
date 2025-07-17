package org.tasos.proj2.applicationservices.services;

import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;

public interface SessionFlagDatesServiceI {

    SessionFlagDatesDomain endGymSession(SessionFlagDatesDomain sessionFlagDatesDTO);

    SessionFlagDatesDomain startGymSession(SessionFlagDatesDomain sessionFlagDatesDTO);

    SessionFlagDatesDomain getLastEndDateOfGymSessions(String userName);

}
