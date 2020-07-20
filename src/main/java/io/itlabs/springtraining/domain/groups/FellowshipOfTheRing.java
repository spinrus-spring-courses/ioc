package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;

import java.util.List;

public class FellowshipOfTheRing implements PersonGroup<Person> {

    private final List<Person> persons;

    public FellowshipOfTheRing(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public List<Person> persons() {
        return persons;
    }
}
