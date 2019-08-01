package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;

import java.util.List;
import java.util.Set;

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
     * @return A set of the walls in the map
     */
    Set<Entity> getWalls();
    /**
     *
     * @param range
     *              New range value.
     */
    void setWeaponRange(int range);

    /**
     *
     * @return Current weapon speed.
     */
    int getWeaponSpeed();

    /**
     *
     * @param range
     *              New speed value.
     */
    void setWeaponSpeed(int range);

    /**
     *
     * @return List of bullets shooted.
     */
    List<Bullet> getBulletList();

    /**
     * Remove the bullet in input from the list of bullets
     * @param bullet
     */
    void removeBullet(final Bullet bullet);
}
