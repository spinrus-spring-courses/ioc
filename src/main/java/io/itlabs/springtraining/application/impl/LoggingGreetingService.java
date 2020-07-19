package io.itlabs.springtraining.application.impl;

import io.itlabs.springtraining.application.GreetingService;
import io.itlabs.springtraining.domain.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingGreetingService implements GreetingService {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingGreetingService.class);

    private String greeting;

    @Override
    public void greet(Person person) {
        LOG.info("{}, {}", greeting, person.getName());
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
