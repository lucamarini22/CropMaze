package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private int weaponDamage;
    private int weaponSpeed;
    private static final double COOLDOWN_TIME = 0.5;
    private int weaponRange;
    private List<Bullet> bulletShoted;
    private final Timer cooldown = Timer.seconds(COOLDOWN_TIME);
    private final GameField gameField;




    /**
     *
     * @param basicWeapon
     *                  The entity weapon.
     */
    public WeaponImpl (final Inventory basicWeapon, final GameField gameField) {
        this.weaponDamage = basicWeapon.damage;
        this.weaponRange = basicWeapon.range;
        this.weaponSpeed = basicWeapon.speed;
        this.bulletShoted = new ArrayList<Bullet>();
        cooldown.update(COOLDOWN_TIME);
        this.gameField = gameField;
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
    public int getWeaponSpeed() { return this.weaponSpeed; }

    @Override
    public void setWeaponSpeed(int speed) { this.weaponSpeed = speed; }

    @Override
    public void shoot(final Direction shootingDirection) {
        if(this.cooldown.isElapsed() && shootingDirection != Direction.NOTHING) {
            Bullet bullet = new Bullet(new BodyBuilder(),
                                        this,
                                        shootingDirection,
                                        getOwner().get().getBody().getPosition(),
                                        gameField.getWalls());
            this.bulletShoted.add(bullet);
            bullet.get(Movement.class).get().update(0);
        }
    }

    @Override
    public List<Bullet> getBulletList() {
        return this.bulletShoted;
    }

    public void removeBullet(final Bullet bullet){
        this.bulletShoted.remove(bullet);
    }
}
