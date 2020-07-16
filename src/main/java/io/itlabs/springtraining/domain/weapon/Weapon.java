package io.itlabs.springtraining.domain.weapon;

public class Weapon {

    enum Type {
        AXE,
        BOW,
        DAGGER,
        SWORD,
        STAFF
    }

    private final Type type;

    public Weapon(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
