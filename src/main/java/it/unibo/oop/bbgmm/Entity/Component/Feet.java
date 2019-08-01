package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.AbstractMovement;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.Set;

/**
 * Permit at the entity to walk
 */
public class Feet extends AbstractMovement {

    private final double walkingSpeed;
    private final Set<Entity> walls;

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
        Point2D newDistanceVector = this.calculateNewDistanceVector(distanceVector);
        if(getOwner().isPresent()){
            Dimension2D dimension  = getOwner().get().getBody().getDimension();
            Rectangle2D shape = new Rectangle2D(newDistanceVector.getX(),newDistanceVector.getY(),
                                                dimension.getWidth(),dimension.getHeight());
            return this.walls.stream().anyMatch(w -> w.getBody().getShape().intersects(shape));
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

    @Override
    public void move( Point2D distanceVector) {
        //utilizzando questo distancevector dovrai modificare la posizione dell'entità
        //ovviamente dovrai modificare il metodo e fare in modo che chieda in input un point2d

        Point2D movement = calculateNewDistanceVector(distanceVector);

        //verifico se c'è un muro, se è presente l'entità non può spostarsi e rimane ferma
        if(wallChecker(distanceVector)){
            setDirectionMovement(Point2D.ZERO);
            updateState();
        }
        else{
            setDirectionMovement(movement);
            updateState();
        }


    }

    /**
     * Set the state of the entity
     */
    private void updateState(){
        if(getDirectionMovement().getX() != 0 || getDirectionMovement().getY() != 0){
            setState(State.WALKING);
        }
        else{
            setState(State.STABLE);
        }
    }

}
