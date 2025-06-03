package org.tasos.proj2.onionarchitecture.springapplication;

import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.SslStoreProvider;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class SslCustomizer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {

    }

    //    @Override
//    public void customize(ConfigurableServletWebServerFactory factory) {
//        Ssl ssl = new Ssl();
//        ssl.setKeyStoreType("PKCS12");
//        ssl.setKeyStore("classpath:keystore.p12");
//        ssl.setKeyStorePassword("proj2pass");
//        ssl.setKeyAlias("proj2alias2");
//        factory.setSsl(ssl);
//        factory.setPort(8443);
//    }
}
