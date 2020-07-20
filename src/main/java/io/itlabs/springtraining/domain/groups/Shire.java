package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Hobbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Shire implements PersonGroup<Hobbit> {

    @Autowired
    private Hobbit frodo;

    @Autowired
    private Hobbit sam;

    @Autowired
    private Hobbit meriadoc;

    @Autowired
    private Hobbit pippin;

    @Override
    public List<Hobbit> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }
}
