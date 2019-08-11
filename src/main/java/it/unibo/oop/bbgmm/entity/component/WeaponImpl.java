package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.*;

import java.util.*;

public class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private static final double COOLDOWN_TIME = 0.5;
    private static final int INITIAL_STEP = 1;
    private int weaponDamage;
    private int weaponSpeed;
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
        this.weaponRange = basicWeapon.range+INITIAL_STEP;
        this.weaponSpeed = basicWeapon.speed;
        this.bulletShoted = new ArrayList<>();
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
        this.weaponRange = range+INITIAL_STEP;
    }

    @Override
    public int getWeaponSpeed() { return this.weaponSpeed; }

    @Override
    public void setWeaponSpeed(int speed) { this.weaponSpeed = speed; }

    @Override
    public Optional<Bullet> shoot(final Direction shootingDirection) {
        Optional<Bullet> bullet = Optional.empty();
        if(this.cooldown.isElapsed() && shootingDirection != Direction.NOTHING) {
            bullet = Optional.of(new Bullet(new BodyBuilder(),
                                        this,
                                        shootingDirection,
                                        getOwner().get().getBody().getPosition(),
                                        gameField.getWalls()));
            this.bulletShoted.add(bullet.get());
            gameField.addEntity(bullet.get());
            bullet.get().get(Movement.class).get().update(0);
            this.cooldown.restart();
        }
        return bullet;
    }

    @Override
    public List<Bullet> getBulletList() {
        return Collections.unmodifiableList(this.bulletShoted);
    }

    @Override
    public void removeBullet(final Bullet bullet){
        this.bulletShoted.remove(bullet);
    }
}
