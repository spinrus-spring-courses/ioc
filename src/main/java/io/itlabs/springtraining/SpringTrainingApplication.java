package io.itlabs.springtraining;

import io.itlabs.springtraining.application.GreetingService;
import io.itlabs.springtraining.domain.groups.FellowshipOfTheRing;
import io.itlabs.springtraining.domain.person.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;

@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(SpringTrainingApplication.class, args);

        final var greetingService = applicationContext.getBean("greetingService", GreetingService.class);
        final var fellowshipOfTheRing = applicationContext.getBean("fellowshipOfTheRing", FellowshipOfTheRing.class);

        greetingService.greet(fellowshipOfTheRing.persons());
    }
}
