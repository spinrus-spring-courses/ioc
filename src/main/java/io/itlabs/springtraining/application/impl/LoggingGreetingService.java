package io.itlabs.springtraining.application.impl;

import io.itlabs.springtraining.application.GreetingService;
import io.itlabs.springtraining.domain.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("greetingService")
public class LoggingGreetingService implements GreetingService {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingGreetingService.class);

    @Value("Hello")
    private String greeting;

    @Override
    public void greet(Person person) {
        LOG.info("{}, {}", greeting, person.getName());
        LOG.info("{}, weapons {}", person.getName(), person.getWeapons());
    }

    @Override
    public void greet(List<Person> persons) {
        persons.forEach(this::greet);
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
