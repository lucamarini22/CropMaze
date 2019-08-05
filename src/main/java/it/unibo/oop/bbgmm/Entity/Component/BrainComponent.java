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
    private static final double RANGE = 0.5;
    private Point2D positionToFollow = Point2D.ZERO;
    private final Entity entityToFollow;
    private final Movement feet;
    private double time = MAX_TIME ;
    private final Life playerLife;


    /**
     * next
     */
    public BrainComponent(final Entity eToFollow, final Movement feet, final Life life) {
        super();
        this.entityToFollow = eToFollow;
        this.feet = feet;
        this.playerLife = life;
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
        Point2D currentPosition = getOwner().get().getBody().getPosition();

        Direction newDirection;

        /*if (this.positionToFollow.getY() < currentPosition.getY()){
            newDirection = Direction.SOUTH;
        }
        else if (this.positionToFollow.getY() > currentPosition.getY()) {
            newDirection = Direction.NORTH;
        }
        else if (this.positionToFollow.getX() < currentPosition.getX()){
            newDirection = Direction.WEST;
        }
        else{
            newDirection = Direction.EAST;
        }*/

        if (this.positionToFollow.getY() < currentPosition.getY()){
            newDirection = Direction.SOUTH;
        }
        else {
            newDirection = Direction.NORTH;
        }

        if(this.positionToFollow.getY() <= currentPosition.getY()+RANGE &&
            positionToFollow.getY() >= currentPosition.getY()-RANGE){
            if (this.positionToFollow.getX() < currentPosition.getX()){
                newDirection = Direction.WEST;
            }
            else{
                newDirection = Direction.EAST;
            }
        }


        //if the alien and the player are collisioning the alien must not move
        if(getOwner().get().getBody().getShape().getBoundsInLocal().intersects(entityToFollow.getBody().getShape().getBoundsInLocal())
           || playerLife.isDead()){
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
