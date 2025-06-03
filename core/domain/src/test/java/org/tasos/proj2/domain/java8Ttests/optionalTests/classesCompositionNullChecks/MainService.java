package org.tasos.proj2.domain.java8Ttests.optionalTests.classesCompositionNullChecks;

import java.util.Optional;

public class MainService {

    // EXAMPLE OF CLEAN CODE
    // WITH JAVA 8- , WE WOULD HAVE MANY NULL CHECKS
    public Integer getMobileScreenWidth(Optional<Mobile> mobile){

        Mobile m1 = new Mobile(0,null,null,null);
        Optional<Mobile> m1opt = Optional.of(m1);

        return m1opt.flatMap(Mobile::getDisplayFeatures)
                .flatMap(DisplayFeatures::getResolution)
                .map(ScreenResolution::getWidth)
                .orElse(0);

    }
}
