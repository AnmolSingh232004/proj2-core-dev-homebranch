package org.tasos.proj2.onionarchitecture.springapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tasos.proj2.BasePackageMarker;

import java.util.Arrays;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
@EnableScheduling
//"org.tasos.proj2.*" registers bean creation (I + Impl) in all app
@ComponentScan(basePackages = {"org.tasos.proj2.*"})
public class Application {

    public static void main(String[] args) {

//        System.setProperty("javax.net.debug", "ssl:handshake");
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }
        };
    }

}
