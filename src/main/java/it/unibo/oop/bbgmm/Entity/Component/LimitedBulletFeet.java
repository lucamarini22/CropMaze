package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Utilities.PlayerMoves;
import javafx.geometry.Point2D;

import java.util.Set;

/**
 * Component used to destroy the Bullet after a number of steps.
 */
public class LimitedBulletFeet extends Feet{

    private static final int STEP = 1;
    private Life lifeComponent;
    private Weapon weapon;
    private final Point2D distanceVector;

    /**
     * Constructor for LimitedBulletFeet.
     *
     * @param walkingSpeed
     *          Entity speed for the movement
     */
    public LimitedBulletFeet(final Weapon weapon, Direction direction, final double walkingSpeed, final Life lifeComponent, final Set<Entity> walls) {
        super(walkingSpeed, walls);
        this.lifeComponent = lifeComponent;
        this.weapon = weapon;
        this.distanceVector = calculateVector(direction);
    }

    @Override
    public void move(Point2D distanceVector) {
        if(lifeComponent.isAlive()){
            super.move(distanceVector);
            if(getPosition().equals(Point2D.ZERO)){
                remove();
            }else{
                lifeComponent.damaged(STEP);
            }
        }
        else {
            remove();
        }
    }

    @Override
    public void update(double delta) {
        move(distanceVector);
    }

    private void remove(){
        weapon.removeBullet((Bullet)getOwner().get());
        getOwner().get().removeEntity();
    }
}
