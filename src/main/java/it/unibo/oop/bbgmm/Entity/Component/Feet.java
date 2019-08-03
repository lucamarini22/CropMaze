package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.AbstractMovement;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

import java.util.Optional;
import java.util.Set;

/**
 * Permit at the entity to walk
 */
public class Feet extends AbstractMovement {

    private double walkingSpeed;
    private Set<Entity> walls;


    /**
     *
     * @param walkingSpeed
     *      entity speed for the movement
     * @param walls
     *      walls for checking collisions
     */
    public Feet(final double walkingSpeed, final Set<Entity> walls){
        super();
        this.walls = walls;
        this.walkingSpeed = walkingSpeed;
    }

    /**
     * attach the component to the entity
     * @param owner
     */
    @Override
    public void attach(Entity owner) {
        super.attach(owner);
        updateState();
    }

    @Override
    public void update(double dt) {
        super.update(dt);
    }

    /**
     * Util method that check wall collision and stop the movement
     * @param distanceVector
     *      The distance vector
     * @return
     *      True if the entity can move
     *
     *
     */
    protected boolean wallChecker(final Point2D distanceVector){
        if(getOwner().isPresent()){
            Dimension2D dimension  = getOwner().get().getBody().getDimension();
            Rectangle shape = new Rectangle(distanceVector.getX(),distanceVector.getY(),
                                                dimension.getWidth(),dimension.getHeight());
            return this.walls.stream().anyMatch(w -> w.getBody().getShape().intersects(shape.getLayoutBounds()));
        }
        return false;
    }

    /**
     * Calculate new distance vector based on the speed
     * @param distanceVector
     *      The distance vector
     * @return
     *      The new distance vector
     */
    private Point2D calculateNewDistanceVector(final Point2D distanceVector){
        final double hf = distanceVector.getX() > 0 ? 1 : distanceVector.getX() < 0 ? -1 : 0;
        final double vf = distanceVector.getY() > 0 ? 1 : distanceVector.getY() < 0 ? -1 : 0;
        return new Point2D(hf * walkingSpeed, vf * walkingSpeed);
    }

    /**
     * Modify the direction that the entity will follow
     * @param vector
     *
     */
    public Direction calculateNewDirection(final Point2D vector){
        if(vector.getY() != vector.getX() ){
            if(vector.getX() == 0 && vector.getY() > 0){
                return Direction.NORTH;
            }
            if(vector.getX() == 0 && vector.getY() < 0){
                return Direction.SOUTH;
            }
            if(vector.getX() < 0 && vector.getY() == 0){
                return Direction.WEST;
            }
            if(vector.getX() > 0 && vector.getY() == 0){
                return Direction.EAST;
            }
        }
        return Direction.NOTHING;

    }

    @Override
    public void move( Point2D distanceVector) {
        //utilizzando questo distancevector dovrai modificare la posizione dell'entità
        //ovviamente dovrai modificare il metodo e fare in modo che chieda in input un point2d

        Point2D movementVector = calculateNewDistanceVector(distanceVector);

        //verifico se c'è un muro, se è presente l'entità non può spostarsi e rimane ferma
        if(wallChecker(movementVector)){
            setPosition(Point2D.ZERO);
        }
        else{
            setPosition(movementVector);
            setDirection(calculateNewDirection(movementVector));
        }
        updateState();
    }

    /**
     * Set the state of the entity
     */
    private void updateState(){
        if(getPosition().getX() != 0 || getPosition().getY() != 0){
            setState(State.WALKING);
        }
        else{
            setState(State.STABLE);
        }
    }

    /**
     * return the walking speed

     * @return the speed
     */
    public double getSpeed(){
        return this.walkingSpeed;
    }

    public void setSpeed(double newSpeed){
        this.walkingSpeed = newSpeed;
    }

}
