package io.itlabs.springtraining.domain.person;

import io.itlabs.springtraining.domain.Named;
import io.itlabs.springtraining.domain.weapon.Weapon;

import java.util.List;

public abstract class Person implements Named {

    protected String name;

    protected List<Weapon> weapons;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
}
