package io.itlabs.springtraining.domain.person;

import io.itlabs.springtraining.domain.Named;

public abstract class Person implements Named {

    protected String name;

    @Override
    public String getName() {
        return name;
    }
}
