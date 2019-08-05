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

    private Point2D positionToFollow = Point2D.ZERO;
    private Entity entityToFollow;
    private double time = 1 ;


    /**
     * next
     */
    public BrainComponent(Entity eToFollow) {
        super();
        this.entityToFollow = eToFollow;
    }


    @Override
    public void update(double delta) {

        this.time = this.time - delta;
        super.update(delta);
        if(this.time <= 0){
            followPlayer();
            this.time = 1;
        }

    }


    @Override
    public void followPlayer(){
        //int min = - 1;
        //int max = 1;

        this.positionToFollow = this.entityToFollow.getBody().getPosition();

        if (this.positionToFollow.getY() < getOwner().get().getBody().getPosition().getY()){
            getOwner().get().getBody().changeDirection(Direction.NORTH);
        }
        else if (this.positionToFollow.getY() > getOwner().get().getBody().getPosition().getY()) {
            getOwner().get().getBody().changeDirection(Direction.SOUTH);
        }
        else if (this.positionToFollow.getX() < getOwner().get().getBody().getPosition().getX()){
            getOwner().get().getBody().changeDirection(Direction.WEST);
        }
        else{
            getOwner().get().getBody().changeDirection(Direction.EAST);
        }
        Direction newDirection = getOwner().get().getBody().getDirection();
        Point2D newPosition = getOwner().get().get(Feet.class).get().calculateVector(newDirection);

        getOwner().get().get(Movement.class).ifPresent( movement -> {
            //double xValue = min + Math.random()*(max-min);
            //double yValue = min + Math.random()*(max-min);
            //this.nextPosition = new Point2D(xValue, yValue);

            movement.move(newPosition);

        });

    }
}
