package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Bullet;
import it.unibo.oop.bbgmm.Entity.Direction;
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
    private final Direction direction;

    /**
     * @param walkingSpeed entity speed for the movement
     */
    public LimitedBulletFeet(final Weapon weapon, Direction direction, final double walkingSpeed, final Life lifeComponent, final Set<Entity> walls) {
        super(walkingSpeed, walls);
        this.lifeComponent = lifeComponent;
        this.weapon = weapon;
        this.direction = direction;
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
        Point2D vector = Point2D.ZERO;

        switch(this.direction){

            case NORTH: vector = new Point2D(PlayerMoves.UP.x, PlayerMoves.UP.y);
                break;
            case SOUTH: vector = new Point2D(PlayerMoves.DOWN.x, PlayerMoves.DOWN.y);
                break;
            case EAST: vector = new Point2D(PlayerMoves.RIGHT.x, PlayerMoves.RIGHT.y);
                break;
            case WEST: vector =  new Point2D(PlayerMoves.LEFT.x, PlayerMoves.LEFT.y);
                break;
        }

        return vector;
    }
}
