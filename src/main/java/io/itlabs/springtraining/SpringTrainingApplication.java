package io.itlabs.springtraining;

import io.itlabs.springtraining.application.GreetingService;
import io.itlabs.springtraining.domain.groups.FellowshipOfTheRing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    public static void main(String[] args) {
        final var applicationContext = SpringApplication.run(SpringTrainingApplication.class, args);

        final var greetingService = applicationContext.getBean("greetingService", GreetingService.class);
        final var fellowshipOfTheRing = applicationContext.getBean("fellowshipOfTheRing", FellowshipOfTheRing.class);

        greetingService.greet(fellowshipOfTheRing.persons());
    }
}
