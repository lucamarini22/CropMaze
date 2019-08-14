package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.AbstractMovement;
import it.unibo.oop.bbgmm.entity.Bullet;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.Entity;
import javafx.geometry.Point2D;

/**
 * component used to destroy the Bullet after a number of steps.
 */
public final class BulletFeetDecorator extends AbstractMovement {

    private static final int STEP = 1;
    private final Life lifeComponent;
    private final Weapon weapon;
    private final Direction direction;
    private final Feet feet;

    /**
     * Constructor for LimitedBulletFeet.
     * @param weapon
     *          The weapon of the player
     * @param direction
     *          The direction of the bullet
     * @param lifeComponent
     *          lifeComponent of the bullet
     * @param feet
     *          The feet to decorate
     */
    public BulletFeetDecorator(final Weapon weapon, final Direction direction, final Life lifeComponent, final Feet feet) {
        super();
        this.lifeComponent = lifeComponent;
        this.weapon = weapon;
        this.direction = direction;
        this.feet = feet;
    }

    /**
     * Method to remove the bullet.
     */
    private void remove() {
        weapon.removeBullet((Bullet) getOwner().get());
        getOwner().get().removeEntity();
    }

    @Override
    public void move(final Point2D direction) {
        if (this.lifeComponent.isAlive()) {
            this.feet.move(direction);
            if (this.feet.getPosition().equals(Point2D.ZERO)) {
                remove();
            } else {
                this.lifeComponent.damaged(STEP);
            }
        } else {
            remove();
        }
    }

    @Override
    public double getSpeed() {
        return this.feet.getSpeed();
    }

    @Override
    public void setSpeed(final double speed) {
        this.feet.setSpeed(speed);
    }

    @Override
    public Point2D calculateVector(final Direction direction) {
        return this.feet.calculateVector(direction);
    }

    @Override
    public void update(final double dt) {
        move(calculateVector(this.direction));
    }

    @Override
    public void attach(final Entity owner) {
        super.attach(owner);
        this.feet.attach(owner);
    }
}
