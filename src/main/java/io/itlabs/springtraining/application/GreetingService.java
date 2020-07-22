package io.itlabs.springtraining.application;

import io.itlabs.springtraining.domain.person.Person;

import java.util.List;

public interface GreetingService {

    void greet(Person person);

    void greet(List<Person> persons);

}
