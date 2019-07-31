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

    @Override
    public void update(double delta) {

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
    private boolean wallChecker(final Point2D distanceVector){
        Point2D newDistanceVector = this.calculatePosition(distanceVector);
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
    private Point2D calculatePosition(final Point2D distanceVector){
        final double hf = distanceVector.getX() > 0 ? 1 : distanceVector.getX() < 0 ? -1 : 0;
        final double vf = distanceVector.getY() > 0 ? 1 : distanceVector.getY() < 0 ? -1 : 0;
        return new Point2D(hf * walkingSpeed, vf * walkingSpeed);
    }

    @Override
    public void move( Direction direction, double speed) {
        //utilizzando questo distancevector dovrai modificare la posizione dell'entità
        //ovviamente dovrai modificare il metodo e fare in modo che chieda in input un point2d
        //if(wallChecker(distanceVector)){
        //    Point2D newDistanceVector = this.calculatePosition(distanceVector);
        //}
        setDesiredDirection(direction);
        super.move(direction, speed);
    }

    private void updateState(Direction direction){
        if(direction.equals(direction.NOTHING)){
            setState(State.STABLE);
        }
        else {
            setState(State.WALKING);
        }
    }

}
