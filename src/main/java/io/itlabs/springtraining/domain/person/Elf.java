package io.itlabs.springtraining.domain.person;

public class Elf extends Person {

    private Elf(String name) {
        this.name = name;
    }

    public static Elf withName(String name) {
        return new Elf(name);
    }
}
