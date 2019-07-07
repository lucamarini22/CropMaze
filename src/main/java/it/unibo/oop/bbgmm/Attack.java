package it.unibo.oop.bbgmm;
/**
 * Models all weapons.
 */
public interface Attack {
    /**
     *
     * @return
     *          Current weapon range.
     */
    int getWeaponRange();

    /**
     *
     * @return
     *          Current weapon damage.
     */
    int getWeaponDamage();

    /**
     *
     * @param range
     *              Amount of range we want to add/subtract from the current range.
     */
    void setWeaponRange(int range);

    /**
     *
     * @param damage
     *              Amount of damage we want to add/subtract from the current damage.
     */
    void setWeaponDamage(int damage);

    /**
     *
     * @param newDirection
     *                      New direction.
     */
    void setDirection(Direction newDirection);

    /**
     *
     * @return
     *          List of bullet the entity shot and that still on the field.
     */
    List<Bullet> getBulletList();

    /**
     * Tells the weapon to shot (create) a bullet.
     */
    void shotBullet();

}
