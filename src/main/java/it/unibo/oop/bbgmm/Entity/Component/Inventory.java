package it.unibo.oop.bbgmm.Entity.Component;

public enum Inventory {
    GUN(10, 10, 1),
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
