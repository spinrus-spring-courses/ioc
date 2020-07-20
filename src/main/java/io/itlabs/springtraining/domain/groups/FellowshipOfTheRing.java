package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FellowshipOfTheRing implements PersonGroup<Person> {

    private final List<Person> persons;

    public FellowshipOfTheRing(@Qualifier("kind") List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public List<Person> persons() {
        return persons;
    }
}
