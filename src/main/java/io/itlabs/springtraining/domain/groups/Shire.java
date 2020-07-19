package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Hobbit;
import io.itlabs.springtraining.domain.person.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Shire implements PersonGroup {

    private final List<Hobbit> hobbits;

    public Shire(List<Hobbit> hobbits) {
        this.hobbits = hobbits;
    }

    @Override
    public List<Person> persons() {
        return hobbits
                .stream()
                .map(Person.class::cast)
                .collect(Collectors.toList());
    }
}
