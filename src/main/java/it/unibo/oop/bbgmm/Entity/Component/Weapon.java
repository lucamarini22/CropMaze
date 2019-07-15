package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;

import java.util.List;

public interface Weapon extends EntityComponent {
    /**
     * Shoots a projectile.
     * @param ownerDirection
     *                  Tells projectile direction.
     */
    void shoot (Direction ownerDirection);

    /**
     *
     * @return Current weapon damage.
     */
    int getWeaponDamage();

    /**
     *
     * @param damage
     *              New damage value in life points.
     */
    void setWeaponDamage(int damage);

    /**
     *
     * @return Current weapon range.
     */
    int getWeaponRange();

    /**
     *
     * @param range
     *              New range value.
     */
    void setWeaponRange(int range);

    /**
     *
     * @return List of bullets shooted.
     */
    List<Bullet> getBulletList();
}
