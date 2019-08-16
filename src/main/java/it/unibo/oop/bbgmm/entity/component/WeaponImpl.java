package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Bullet;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.GameField;
import it.unibo.oop.bbgmm.entity.Movement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Manage a weapon component.
 */
public final class WeaponImpl extends AbstractEntityComponent implements Weapon {

    private static final double COOLDOWN_TIME = 0.2;
    private static final int INITIAL_STEP = 1;
    private int weaponDamage;
    private int weaponSpeed;
    private int weaponRange;
    private final List<Bullet> bulletShoted;
    private final Timer cooldown = Timer.seconds(COOLDOWN_TIME);
    private final GameField gameField;

    /**
     * @param basicWeapon
     *      Th weapon used.
     * @param gameField
     *      The game field instance.
     */
    public WeaponImpl(final Inventory basicWeapon, final GameField gameField) {
        super();
        this.weaponDamage = basicWeapon.getDamage();
        this.weaponRange = basicWeapon.getRange() + INITIAL_STEP;
        this.weaponSpeed = basicWeapon.getSpeed();
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
    public void update(final double delta) {
        cooldown.update(delta);
    }

    @Override
    public void setWeaponRange(final int range) {
        this.weaponRange = range + INITIAL_STEP;
    }

    @Override
    public int getWeaponSpeed() {
        return this.weaponSpeed;
    }

    @Override
    public void setWeaponSpeed(final int speed) {
        this.weaponSpeed = speed;
    }

    @Override
    public Optional<Bullet> shoot(final Direction shootingDirection) {
        Optional<Bullet> bullet = Optional.empty();
        if (this.cooldown.isElapsed() && shootingDirection != Direction.NOTHING) {
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
    public void removeBullet(final Bullet bullet) {
        this.bulletShoted.remove(bullet);
    }
}
