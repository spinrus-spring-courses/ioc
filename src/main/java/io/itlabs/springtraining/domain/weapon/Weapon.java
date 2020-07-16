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

    private boolean broken;

    public Weapon(Type type) {
        this.type = type;
        this.broken = false;
    }

    public Type getType() {
        return type;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }
}
