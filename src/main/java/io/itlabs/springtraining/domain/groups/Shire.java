package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Hobbit;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Shire implements PersonGroup<Hobbit> {

    private final Hobbit frodo;

    private final Hobbit sam;

    private final Hobbit meriadoc;

    private final Hobbit pippin;

    public Shire(Hobbit frodo, Hobbit sam, Hobbit meriadoc, Hobbit pippin) {
        this.frodo = frodo;
        this.sam = sam;
        this.meriadoc = meriadoc;
        this.pippin = pippin;
    }

    @Override
    public List<Hobbit> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }
}
