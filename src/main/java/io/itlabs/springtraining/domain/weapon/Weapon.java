package io.itlabs.springtraining.domain.weapon;

public class Weapon {

    public enum Type {
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

    private boolean broken = false;

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
