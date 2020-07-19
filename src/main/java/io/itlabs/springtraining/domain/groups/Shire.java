package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;

import java.util.List;

import static java.util.Collections.emptyList;

public class Shire implements PersonGroup {

    @Override
    public List<Person> persons() {
        return emptyList();
    }
}
