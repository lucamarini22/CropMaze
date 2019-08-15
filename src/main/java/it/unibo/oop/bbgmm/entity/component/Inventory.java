package it.unibo.oop.bbgmm.entity.component;

/**
 * Inventory for all the possible weapon.
 */
public enum Inventory {
    /**
     * Models a gun.
     */
    GUN(10, 10, 1),
    /**
     * Models a punch.
     */
    PUNCH(5, 20, 1),
    /**
     * Models a RPG.
     */
    RPG(30, 100, 1);

    private int damage;
    private int range;
    private int speed;

    Inventory(final int range, final int damage, final int speed) {
        this.range = range;
        this.damage = damage;
        this.speed = speed;
    }

    /**
     * @return weapon damage.
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * @return weapon range.
     */
    public int getRange() {
        return this.range;
    }

    /**
     * @return weapon fire rate.
     */
    public int getSpeed() {
        return this.speed;
    }
}
