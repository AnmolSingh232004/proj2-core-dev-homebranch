package org.tasos.proj2.springrestservices.controller.util.auth;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.tasos.proj2.auth.util.SecurityUtils;
import org.tasos.proj2.springrestservices.controller.CalendarController;

public class JWTUtils {

    private static final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    public static String getUserNameFromJWT() {
        Optional<String> userData = SecurityUtils.getCurrentUserLogin();
        String userName = userData.orElseGet(() -> {return "NoUser";});
        log.debug("USER FROM SECURITY CONTEXT: " + userName);
        log.debug("is current user in role: " + SecurityUtils.isCurrentUserInRole("ROLE_USER"));
        return userName;
    }
}
