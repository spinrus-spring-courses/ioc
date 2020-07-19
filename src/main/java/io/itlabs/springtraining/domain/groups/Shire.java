package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Shire implements PersonGroup {

    private final Person frodo;

    private final Person sam;

    private final Person meriadoc;

    private final Person pippin;

    public Shire(Person frodo, Person sam, Person meriadoc, Person pippin) {
        this.frodo = frodo;
        this.sam = sam;
        this.meriadoc = meriadoc;
        this.pippin = pippin;
    }

    @Override
    public List<Person> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }
}
