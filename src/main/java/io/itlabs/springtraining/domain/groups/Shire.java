package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Shire implements PersonGroup {

    @Autowired
    private Person frodo;

    @Autowired
    private Person sam;

    @Autowired
    private Person meriadoc;

    @Autowired
    private Person pippin;

    @Override
    public List<Person> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }
}
