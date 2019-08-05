package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Movement;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * permite to follow a determinate entity and change the desired direciton
 */
public class BrainComponent extends AbstractEntityComponent implements Brain {

    private static final double MAX_TIME = 0.2;
    private Point2D positionToFollow = Point2D.ZERO;
    private final Entity entityToFollow;
    private final Movement feet;
    private double time = MAX_TIME ;


    /**
     * next
     */
    public BrainComponent(final Entity eToFollow, final Movement feet) {
        super();
        this.entityToFollow = eToFollow;
        this.feet = feet;
    }


    @Override
    public void update(double delta) {
        this.time = this.time - delta;
        super.update(delta);
        if(this.time <= 0){
            followPlayer();
            this.time = MAX_TIME;
        }
    }

    @Override
    public void followPlayer(){
        //int min = - 1;
        //int max = 1;

        this.positionToFollow = this.entityToFollow.getBody().getPosition();

        Direction newDirection = Direction.NOTHING;

        if (this.positionToFollow.getY() < getOwner().get().getBody().getPosition().getY()){
            newDirection = Direction.SOUTH;
        }
        else if (this.positionToFollow.getY() > getOwner().get().getBody().getPosition().getY()) {
            newDirection = Direction.NORTH;
        }
        else if (this.positionToFollow.getX() < getOwner().get().getBody().getPosition().getX()){
            newDirection = Direction.WEST;
        }
        else{
            newDirection = Direction.EAST;
        }

        //if the alien and the player are collisioning the alien must not move
        if(getOwner().get().getBody().getShape().getBoundsInLocal().intersects(entityToFollow.getBody().getShape().getBoundsInLocal())){
            newDirection = Direction.NOTHING;
        }

        Point2D newPosition = feet.calculateVector(newDirection);

        /*getOwner().get().get(Movement.class).ifPresent( movement -> {
            //double xValue = min + Math.random()*(max-min);
            //double yValue = min + Math.random()*(max-min);
            //this.nextPosition = new Point2D(xValue, yValue);

            movement.move(newPosition);

        });*/
        feet.move(newPosition);
    }
}
