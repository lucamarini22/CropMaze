package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Utilities.PlayerMoves;
import javafx.geometry.Point2D;

import java.util.Set;

/**
 * Component used to destroy the Bullet after a number of steps
 */
public class LimitedBulletFeet extends Feet{

    private static final int STEP = 1;
    private Life lifeComponent;
    private Weapon weapon;
    private final Point2D distanceVector;

    /**
     * @param walkingSpeed entity speed for the movement
     */
    public LimitedBulletFeet(final Weapon weapon, final double walkingSpeed, final Life lifeComponent,final Set<Entity> walls) {
        super(walkingSpeed, walls);
        this.lifeComponent = lifeComponent;
        this.weapon = weapon;
        this.distanceVector = calculateVector();
    }

    @Override
    public void move(Point2D distanceVector) {
        if(lifeComponent.isAlive()){
            super.move(distanceVector);
        }
        else{
            remove();
        }
        lifeComponent.damaged(STEP);
    }

    @Override
    public void update(double delta) {
        if(wallChecker(distanceVector)){
            move(distanceVector);
        }
        else{
            remove();
        }
    }

    private void remove(){
        weapon.removeBullet((Bullet)getOwner().get());
        getOwner().get().destroy();
    }

    private Point2D calculateVector(){
        double speed = getSpeed();
        Point2D vector = Point2D.ZERO;

        switch(getOwner().get().getBody().getDirection()){

            case NORTH: vector = new Point2D(PlayerMoves.UP.x*speed, PlayerMoves.UP.y*speed);
                break;
            case SOUTH: vector = new Point2D(PlayerMoves.DOWN.x*speed, PlayerMoves.DOWN.y*speed);
            break;
            case EAST: vector = new Point2D(PlayerMoves.RIGHT.x*speed, PlayerMoves.RIGHT.y*speed);
            break;
            case WEST: vector =  new Point2D(PlayerMoves.LEFT.x*speed, PlayerMoves.LEFT.y*speed);
            break;
        }

        return vector;
    }
}
