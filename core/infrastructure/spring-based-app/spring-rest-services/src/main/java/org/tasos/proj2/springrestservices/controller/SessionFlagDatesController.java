package org.tasos.proj2.springrestservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tasos.proj2.applicationservices.services.GlobalFlagServiceI;
import org.tasos.proj2.applicationservices.services.SessionFlagDatesServiceI;
import org.tasos.proj2.domain.globalsessionflags.GlobalFlagDomain;
import org.tasos.proj2.domain.globalsessionflags.SessionFlagDatesDomain;
import org.tasos.proj2.springrestservices.controller.util.HeaderUtil;
import org.tasos.proj2.springrestservices.controller.util.ResponseUtil;
import org.tasos.proj2.springrestservices.controller.util.auth.JWTUtils;
import org.tasos.proj2.springrestservices.dto.globalFlag.GlobalFlagRequest;
import org.tasos.proj2.springrestservices.dto.globalFlag.GlobalFlagResponse;
import org.tasos.proj2.springrestservices.dto.sessionFlagDates.SessionFlagDatesRequest;
import org.tasos.proj2.springrestservices.dto.sessionFlagDates.SessionFlagDatesResponse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hibernate.id.IdentifierGenerator.ENTITY_NAME;

@RestController
@CrossOrigin(origins = {"https://proj2.localhost", "https://localhost:4201", "https://localhost:9003", "https://157.230.113.41"})
@RequestMapping("/api/proj2")
public class SessionFlagDatesController {

    private final Logger log = LoggerFactory.getLogger(SessionFlagDatesController.class);

    private final SessionFlagDatesServiceI sessionFlagDatesService;

    private final GlobalFlagServiceI sessionFlagDatesGlobalFlagService;

    public SessionFlagDatesController(SessionFlagDatesServiceI sessionFlagDatesService,
                                      GlobalFlagServiceI sessionFlagDatesGlobalFlagService) {
        this.sessionFlagDatesService = sessionFlagDatesService;
        this.sessionFlagDatesGlobalFlagService = sessionFlagDatesGlobalFlagService;
    }

    @PostMapping("/session-flag-dates/gym/end")
    public SessionFlagDatesResponse endGymSession(@RequestBody SessionFlagDatesRequest sessionFlagDatesReq) throws URISyntaxException {

        // Add JWT username
        String userName = JWTUtils.getUserNameFromJWT();

        // Request to Domain
        SessionFlagDatesDomain sessionFlagDatesDomain = new SessionFlagDatesDomain(null, new GlobalFlagDomain(userName),
                sessionFlagDatesReq.getStartDate(), sessionFlagDatesReq.getEndDate());

        SessionFlagDatesDomain domainResp = sessionFlagDatesService.endGymSession(sessionFlagDatesDomain);

        // DONT NEED TO RETURN ANYTHING - ITS A POST ACTION.
        // Domain to Response
        SessionFlagDatesResponse resp = new SessionFlagDatesResponse();
        resp.setStartDate(resp.getStartDate());
        resp.setEndDate(resp.getEndDate());
        return resp;

    }

    @PostMapping("/session-flag-dates/gym/start")
    public SessionFlagDatesResponse startGymSession(@RequestBody SessionFlagDatesRequest sessionFlagDatesReq) throws URISyntaxException {

        // Add JWT username
        String userName = JWTUtils.getUserNameFromJWT();

        // Request to Domain
        SessionFlagDatesDomain sessionFlagDatesDomain = new SessionFlagDatesDomain(null, new GlobalFlagDomain(userName),
                sessionFlagDatesReq.getStartDate(), sessionFlagDatesReq.getEndDate());

        SessionFlagDatesDomain domainResp = sessionFlagDatesService.startGymSession(sessionFlagDatesDomain);

        // DONT NEED TO RETURN ANYTHING - ITS A POST ACTION.
        // Domain to Response
        SessionFlagDatesResponse resp = new SessionFlagDatesResponse();
        resp.setStartDate(resp.getStartDate());
        resp.setEndDate(resp.getEndDate());
        return resp;
    }


    /**
     *  Decide if editable (current date > gym session end)
     *  Get last end date from session_flag_dates
     */
    @GetMapping("/session-flag-dates/gym-session/last/end-date")
    public ResponseEntity<SessionFlagDatesResponse> getLastEndDateOfGymSessions() {
        log.debug("REST request to get end date of last gym session");

        // Add JWT username
        String userName = JWTUtils.getUserNameFromJWT();

        SessionFlagDatesDomain domainResp = sessionFlagDatesService.getLastEndDateOfGymSessions(userName);

        // Domain to Response
        SessionFlagDatesResponse resp = new SessionFlagDatesResponse();
        if (domainResp != null && domainResp.getStartDate() != null) {
            resp.setStartDate(domainResp.getStartDate());
        } else {
            resp.setStartDate(null);
        }
        if (domainResp != null &&  domainResp.getEndDate() != null) {
            resp.setEndDate(domainResp.getEndDate());
        } else {
            resp.setEndDate(null);
        }

        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(resp));
    }

    // ---------------  Global Flag --------------- //

    @PostMapping("/global-flags")
    public ResponseEntity<GlobalFlagResponse> createGlobalFlag(@RequestBody GlobalFlagRequest globalFlagReq) throws URISyntaxException {

        // Add JWT username
        globalFlagReq.setUserName(JWTUtils.getUserNameFromJWT());

        // Request to Domain
        GlobalFlagDomain globalFlagDomain = new GlobalFlagDomain(null, globalFlagReq.getName(),
                globalFlagReq.getDescription(), globalFlagReq.getIsActive(), globalFlagReq.getUserName());

        GlobalFlagDomain globalFlag = sessionFlagDatesGlobalFlagService.createGlobalFlag(globalFlagDomain);

        // Domain to Response
        GlobalFlagResponse resp = getGlobalFlagResponse(globalFlag);

        return ResponseEntity.created(new URI("/api/global-flags/" + globalFlag.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("applicationName", false, ENTITY_NAME, globalFlag.getId().toString()))
                .body(resp);
    }

    @PutMapping("/global-flags")
    public ResponseEntity<GlobalFlagResponse> updateGlobalFlag(@RequestBody GlobalFlagRequest globalFlagReq) throws URISyntaxException {

        // Add JWT username
        globalFlagReq.setUserName(JWTUtils.getUserNameFromJWT());

        // Request to Domain
        GlobalFlagDomain globalFlagDomain = new GlobalFlagDomain(globalFlagReq.getId(), globalFlagReq.getName(),
                globalFlagReq.getDescription(), globalFlagReq.getIsActive(), globalFlagReq.getUserName());

        GlobalFlagDomain globalFlag = sessionFlagDatesGlobalFlagService.updateGlobalFlag(globalFlagDomain);

        // Domain to Response
        GlobalFlagResponse resp = getGlobalFlagResponse(globalFlag);

        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("applicationName", false, ENTITY_NAME, globalFlag.getName()))
                .body(resp);
    }


    @GetMapping("/global-flags")
    public List<GlobalFlagResponse> getAllGlobalFlagsPerUser() {
        // Add JWT username
       String userName = JWTUtils.getUserNameFromJWT();

        List<GlobalFlagDomain> globalFlags = sessionFlagDatesGlobalFlagService.getAllGlobalFlagsPerUser(userName);

        List<GlobalFlagResponse> globalFlagsResponses = globalFlags.stream()
                .map(SessionFlagDatesController::getGlobalFlagResponse)
                .collect(Collectors.toList());

        return globalFlagsResponses;
    }

    // Not used
    @GetMapping("/global-flags/{id}")
    public ResponseEntity<GlobalFlagResponse> getGlobalFlag(@PathVariable Long id) throws Exception {
        GlobalFlagDomain globalFlag = sessionFlagDatesGlobalFlagService.getGlobalFlag(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(getGlobalFlagResponse(globalFlag)));
    }

    // Util
    private static GlobalFlagResponse getGlobalFlagResponse(GlobalFlagDomain globalFlag) {
        GlobalFlagResponse resp = new GlobalFlagResponse();
        resp.setId(globalFlag.getId());
        resp.setName(globalFlag.getName());
        resp.setDescription(globalFlag.getDescription());
        resp.setUserName(globalFlag.getUserName());

        if (globalFlag.getIsActive().booleanValue() == true) {
            resp.setIsActive("true");
        } else {
            resp.setIsActive("false");
        }

        return resp;
    }

}