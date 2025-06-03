package org.tasos.proj2.applicationlogic.services.implementation;

import org.tasos.proj2.applicationlogic.services.implementation.util.ThrowingConsumer;
import org.tasos.proj2.applicationservices.services.ActivityServiceI;
import org.tasos.proj2.applicationservices.services.GlobalFlagServiceI;
import org.tasos.proj2.applicationservices.services.SessionFlagDatesServiceI;
import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.domain.activity.ActivityType;
import org.tasos.proj2.domain.activity.CreateActivityDTO;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;
import org.tasos.proj2.repositoryinterface.activity.ActivityRepositoryI;
import org.tasos.proj2.repositoryinterface.activity.ActivityTypeRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.GlobalFlagRepositoryI;
import org.tasos.proj2.repositoryinterface.globalsessionflags.SessionFlagDatesRepositoryI;

import javax.inject.Inject;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class SessionFlagDatesService implements SessionFlagDatesServiceI, GlobalFlagServiceI {

    private final SessionFlagDatesRepositoryI sessionFlagDatesRepository;

    private final GlobalFlagRepositoryI globalFlagRepository;

    @Inject
    public SessionFlagDatesService(SessionFlagDatesRepositoryI sessionFlagDatesRepository,
                                   GlobalFlagRepositoryI globalFlagRepository) {
        this.sessionFlagDatesRepository = sessionFlagDatesRepository;
        this.globalFlagRepository = globalFlagRepository;
    }

    /**
     * Start a Gym session
     *
     * @param sessionFlagDatesDomain
     * @return
     */
    @Override
    public SessionFlagDatesDomain startGymSession(SessionFlagDatesDomain sessionFlagDatesDomain) {
        // Find gym Global Flag
        GlobalFlagDomain gymGlobalFlag = globalFlagRepository.findByNameEqualsAndUserName("gymSession", sessionFlagDatesDomain.getGlobalFlag().getUserName());
        // Save record on SFD table for new gym session
        sessionFlagDatesDomain.setGlobalFlag(gymGlobalFlag);
        SessionFlagDatesDomain gymSessionFlagDatesCreated = sessionFlagDatesRepository.save(sessionFlagDatesDomain);

        return gymSessionFlagDatesCreated;
    }

    /**
     * End current Gym session
     *
     * @param sessionFlagDatesDomain
     * @return
     */
    @Override
    public SessionFlagDatesDomain endGymSession(SessionFlagDatesDomain sessionFlagDatesDomain) {
        // Find gym Global Flag
        GlobalFlagDomain gymGlobalFlag = globalFlagRepository.findByNameEqualsAndUserName("gymSession", sessionFlagDatesDomain.getGlobalFlag().getUserName());
        // Find SessionFlagDates for gym flag
        SessionFlagDatesDomain gymSessionFlagDates = sessionFlagDatesRepository.findFirstByEndDateIsNullAndGlobalFlag(gymGlobalFlag);
        // Apply the end date
        gymSessionFlagDates.setEndDate(sessionFlagDatesDomain.getEndDate());
        SessionFlagDatesDomain gymSessionFlagDatesEdited = sessionFlagDatesRepository.save(gymSessionFlagDates);

        return gymSessionFlagDatesEdited;
    }

    @Override
    public SessionFlagDatesDomain getLastEndDateOfGymSessions(String userName) {
        // Find gym Global Flag
        GlobalFlagDomain gymGlobalFlagOfUser = globalFlagRepository.findByNameEqualsAndUserName("gymSession", userName);

        return sessionFlagDatesRepository.findFirstByGlobalFlagOrderByEndDateDesc(gymGlobalFlagOfUser);
    }



    //------------- GLOBAL FLAG BC -------------//

    @Override
    public GlobalFlagDomain createGlobalFlag(GlobalFlagDomain globalFlag) throws URISyntaxException {
        return globalFlagRepository.save(globalFlag);
    }

    @Override
    public GlobalFlagDomain updateGlobalFlag(GlobalFlagDomain globalFlag) {
        GlobalFlagDomain globalFlagFound = globalFlagRepository.findByNameEqualsAndUserName(globalFlag.getName(), globalFlag.getUserName());
        // Update with active or non-active
        globalFlagFound.setIsActive(globalFlag.getIsActive());
       return globalFlagRepository.save(globalFlagFound);
    }

    @Override
    public List<GlobalFlagDomain> getAllGlobalFlagsPerUser(String userName) {
        return globalFlagRepository.findAllByUserName(userName);
    }


    // Not used
    @Override
    public GlobalFlagDomain getGlobalFlag(Long id) throws Exception {
       return globalFlagRepository.findById(id);
    }
}
