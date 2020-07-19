package io.itlabs.springtraining;

import io.itlabs.springtraining.domain.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.util.stream.Stream;

@SpringBootApplication
@ImportResource("classpath:spring-configuration.xml")
public class SpringTrainingApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringTrainingApplication.class);

    public static void main(String[] args) {
        final var applicationContext = SpringApplication.run(SpringTrainingApplication.class, args);
        Stream.of(applicationContext.getBeanNamesForType(Person.class)).forEach(LOG::info);
    }

}
