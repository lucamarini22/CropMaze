package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Direction;

import java.util.ArrayList;

public class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private int weaponDamage;
    private static final double COOLDOWN_TIME = 0.5;
    private int weaponRange;
    private List<Bullet> bulletShoted;
    private final Timer cooldown = Timer.seconds(COOLDOWN_TIME);


    /**
     *
     * @param basicWeapon
     *                  The entity weapon.
     */
    public WeaponImpl (final Inventory basicWeapon) {
        this.weaponDamage = basicWeapon.damage;
        this.weaponRange = basicWeapon.range;
        this.bulletShoted = new ArrayList<Bullet>();
        cooldown.update(COOLDOWN_TIME);
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
    public void update(double delta) {
        cooldown.update(delta);
    }

    @Override
    public void setWeaponRange(final int range) {
        this.weaponRange = range;
    }

    @Override
    public void shoot(final Direction ownerDirection) {
        if(this.cooldown.isElapsed()) {
            this.bulletShoted(new Bullet(ownerDirection, this.weaponRange, this.weaponDamage));
        }
    }

    @Override
    public List<Bullet> getBulletList() {
        return this.bulletShoted;
    }

}
