package io.itlabs.springtraining.domain;

import io.itlabs.springtraining.domain.person.Person;

import java.util.List;

public interface PersonGroup<T extends Person> {

    List<T> persons();
}
