package io.itlabs.springtraining.domain.person;

public class MageFactory {

    public Mage mage(String name) {
        return new Mage(name);
    }
}
