package io.itlabs.springtraining.domain.person;

public class MageFactory {

    public Mage withName(String name) {
        return new Mage(name);
    }
}
