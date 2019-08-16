package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Bullet;
import it.unibo.oop.bbgmm.entity.Direction;

import java.util.List;
import java.util.Optional;

/**
 * Interface for weapon.
 */
public interface Weapon extends EntityComponent {
    /**
     * Shoots a projectile.
     * @param ownerDirection
     *      Tells projectile direction.
     * @return The bullet shot.
     */
    Optional<Bullet> shoot(Direction ownerDirection);

    /**
     * @return Current weapon damage.
     */
    int getWeaponDamage();

    /**
     *
     * @param damage
     *      New damage value in life points.
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
     * Remove the bullet in input from the list of bullets.
     * @param bullet
     *      the bullet to remove
     */
    void removeBullet(Bullet bullet);
}
