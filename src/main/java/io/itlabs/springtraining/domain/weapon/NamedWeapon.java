package io.itlabs.springtraining.domain.weapon;

import io.itlabs.springtraining.domain.Named;

public class NamedWeapon extends Weapon implements Named {

    private final String name;

    public NamedWeapon(Type type, String name) {
        super(type);
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
