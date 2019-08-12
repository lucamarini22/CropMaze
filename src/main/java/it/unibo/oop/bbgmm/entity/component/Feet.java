package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.AbstractMovement;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.Entity;
import it.unibo.oop.bbgmm.utilities.PlayerMoves;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import java.util.Set;

/**
 * Allows the entity to walk.
 */
public class Feet extends AbstractMovement {

    private double walkingSpeed;
    private final Set<Entity> walls;


    /**
     * Constructor.
     * @param walkingSpeed
     *      entity speed for the movement
     * @param walls
     *      walls for checking collisions
     */
    public Feet(final double walkingSpeed, final Set<Entity> walls) {
        super();
        this.walls = walls;
        this.walkingSpeed = walkingSpeed;
    }

    /**
     * attach the component to the entity.
     * @param owner
     */
    @Override
    public void attach(final Entity owner) {
        super.attach(owner);
        //setState(State.STABLE);
        //updateState();
    }

    @Override
    public void update(final double dt) {
        super.update(dt);
    }

    /**
     * Util method that check wall collision and stop the movement.
     * @param distanceVector
     *      The distance vector.
     * @return
     *      True if the entity can move.
     */
    private boolean wallChecker(final Point2D distanceVector) {
        if (getOwner().isPresent()) {
            final Dimension2D dimension  = getOwner().get().getBody().getDimension();
            final Rectangle shape = new Rectangle(distanceVector.getX(), distanceVector.getY(),
                                                dimension.getWidth(), dimension.getHeight());
            return this.walls.stream().anyMatch(w -> w.getBody().getShape().intersects(shape.getLayoutBounds()));
        }
        return false;
    }

    /**
     * Calculate new distance vector based on the speed.
     * @param distanceVector
     *      The distance vector.
     * @return
     *      The new distance vector.
     */
    private Point2D calculateNewDistanceVector(final Point2D distanceVector) {
        final double hf = distanceVector.getX() > 0 ? 1 : distanceVector.getX() < 0 ? -1 : 0;
        final double vf = distanceVector.getY() > 0 ? 1 : distanceVector.getY() < 0 ? -1 : 0;

        return new Point2D(hf * walkingSpeed, vf * walkingSpeed);
    }

    /**
     * Modify the direction that the entity will follow.
     * @param vector
     *          Point2D to analyze.
     * @return
     *          vector direction.
     */
    public Direction calculateNewDirection(final Point2D vector) {
        if (vector.getY() != vector.getX()) {
            if (vector.getX() == 0 && vector.getY() > 0) {
                return Direction.NORTH;
            }
            if (vector.getX() == 0 && vector.getY() < 0) {
                return Direction.SOUTH;
            }
            if (vector.getX() < 0 && vector.getY() == 0) {
                return Direction.WEST;
            }
            if (vector.getX() > 0 && vector.getY() == 0) {
                return Direction.EAST;
            }
        }
        return getOwner().get().getBody().getDirection();
    }

    @Override
    public void move(final Point2D distanceVector) {
        final Point2D movementVector = calculateNewDistanceVector(distanceVector);
        final Point2D temp = new Point2D(movementVector.getX(), movementVector.getY());
        final Point2D newPos = temp.add(getOwner().get().getBody().getPosition());

        if (wallChecker(newPos)) {
            setPosition(Point2D.ZERO);
            //setState(State.STABLE);
            //System.out.println("entity stable, find a wall");
        } else {
            setPosition(movementVector);
            setDirection(calculateNewDirection(movementVector));

            /*
            if(getPosition().equals(Point2D.ZERO)){
                setState(State.STABLE);
                System.out.println("entity stable - move method --> State =  " + getState());
            }
            else {
                setState(State.WALKING);
                System.out.println("entity walking -> State = " + getState());
            }*/
        }
        //System.out.println(" " + getState());
        //setPosition(Point2D.ZERO);
        //setState(State.STABLE);
        updateState();
        //System.out.println("end of movement -> State = " + getState());

    }

    /**
     * Set the state of the entity.
     */
    private void updateState() {
        if (!getPosition().equals(Point2D.ZERO)) {
            setState(State.WALKING);
        } else {
            setDirection(Direction.NOTHING);
            setState(State.STABLE);
        }
    }

    /**
     * return the walking speed.
     * @return the speed.
     */
    public double getSpeed() {
        return this.walkingSpeed;
    }

    /**
     * Setter for the speed.
     * @param newSpeed
     *          new speed to set
     */
    public void setSpeed(final double newSpeed) {
        this.walkingSpeed = newSpeed;
    }

    @Override
    public Point2D calculateVector(final Direction direction) {
        Point2D vector = Point2D.ZERO;

        switch (direction) {
            case NORTH:
                vector = new Point2D(PlayerMoves.UP.x, PlayerMoves.UP.y);
                break;
            case SOUTH:
                vector = new Point2D(PlayerMoves.DOWN.x, PlayerMoves.DOWN.y);
                break;
            case EAST:
                vector = new Point2D(PlayerMoves.RIGHT.x, PlayerMoves.RIGHT.y);
                break;
            case WEST:
                vector =  new Point2D(PlayerMoves.LEFT.x, PlayerMoves.LEFT.y);
                break;
            default:
                break;
        }

        return vector;
    }
}
