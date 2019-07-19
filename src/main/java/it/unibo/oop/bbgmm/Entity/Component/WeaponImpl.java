package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;

import java.util.ArrayList;
import java.util.List;

public class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private int weaponDamage;
    private int weaponSpeed;
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
        this.weaponSpeed = basicWeapon.speed;
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

    /**
     * Sychronizes the component
     *
     * @param delta The time passed since the last call in seconds
     */
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
            this.bulletShoted.add(new Bullet(new BodyBuilder(),
                                             ownerDirection,
                                             this.weaponRange,
                                             this.weaponDamage,
                                             getOwner().get().getBody().getPosition(),
                                             weaponSpeed));
        }
    }

    @Override
    public List<Bullet> getBulletList() {
        return this.bulletShoted;
    }


}
