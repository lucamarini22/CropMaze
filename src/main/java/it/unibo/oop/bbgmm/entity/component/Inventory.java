package it.unibo.oop.bbgmm.entity.component;

public enum Inventory {
    GUN(10, 100, 1),
    PUNCH(5, 20, 1),
    RPG(30, 100, 1);

    int damage;
    int range;
    int speed;

    Inventory(final int range, final int damage, final int speed) {
        this.range = range;
        this.damage = damage;
        this.speed = speed;
    }
}
