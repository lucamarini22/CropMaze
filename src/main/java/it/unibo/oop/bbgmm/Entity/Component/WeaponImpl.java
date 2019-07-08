package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;

import java.util.ArrayList;
import java.util.List;

public class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private int weaponDamage;
    private int weaponRange;
    private List<Bullet> bulletShoted;


    /**
     *
     * @param basicWeapon
     *                  The entity weapon.
     */
    public WeaponImpl (final Inventory basicWeapon) {
        this.weaponDamage = basicWeapon.damage;
        this.weaponRange = basicWeapon.range;
        this.bulletShoted = new ArrayList<Bullet>();
    }

    @Override
    public int getWeaponDamage() {
        return this.weaponDamage;
    }

    @Override
    public void setWeaponDamage(final int damage) {
        this.weaponDamage = damage;
    }

    @Override
    public int getWeaponRange() {
        return this.weaponRange;
    }

    @Override
    public void setWeaponRange(final int range) {
        this.weaponRange = range;
    }

    @Override
    public void shoot(final Direction ownerDirection) {
        this.bulletShoted.add(new Bullet(ownerDirection, this.weaponRange, this.weaponDamage));
    }

    @Override
    public List<Bullet> getBulletList() {
        return this.bulletShoted;
    }

    /**
     * Sychronizes the component
     *
     * @param delta The time passed since the last call in seconds
     */
    @Override
    public void update(double delta) {

    }
}
