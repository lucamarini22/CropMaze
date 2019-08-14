package it.unibo.oop.bbgmm.entity;
import it.unibo.oop.bbgmm.entity.component.AbstractEntityComponent;
import javafx.geometry.Point2D;

/**
 * Base class for movement.
 */
public abstract class AbstractMovement extends AbstractEntityComponent implements Movement {
    private State currentState = Movement.State.STABLE;
    private Point2D desiredPosition = Point2D.ZERO;
    private Direction direction = Direction.NOTHING;
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public State getState() {
        return currentState;
    }

    /**
     * Applies movement.
     * @param dt
     *          delta
     */
    @Override
    public void update(final double dt) {
    }

    /**
     * Set the new State.
     * @param newState
     *          new State to set.
     */
    protected final void setState(final State newState) {
        if (!currentState.equals(newState)) {
            currentState = newState;
        }
    }

    /**
     *Method to get the actual position.
     * @return the desired movement vector.
     */
    public final Point2D getPosition() {
        return desiredPosition;
    }

    /**
     * Set the new movement vector.
     * @param newMovement
     *              new movement to do.
     *
     */
    protected final void setPosition(final Point2D newMovement) {
        this.desiredPosition = newMovement;
        getOwner().ifPresent(o -> ((Entity) o).getBody().addPosition(newMovement));
    }

    /**
     * Get the actual direction.
     * @return the direction.
     */
    protected final Direction getDirection() {
        return direction;
    }

    /**
     * set the direction.
     * @param newDirection
     *          new direction to set.
     */
    protected final void setDirection(final Direction newDirection) {
        this.direction = newDirection;
        getOwner().ifPresent(o -> ((Entity) o).getBody().changeDirection(direction));
    }
}
