package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Hobbit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Shire implements PersonGroup<Hobbit> {

    private final List<Hobbit> hobbits;

    public Shire(List<Hobbit> hobbits) {
        this.hobbits = hobbits;
    }

    @Override
    public List<Hobbit> persons() {
        return hobbits;
    }
}
