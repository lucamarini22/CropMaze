package it.unibo.oop.bbgmm.entity;
import it.unibo.oop.bbgmm.entity.component.EntityComponent;
import javafx.geometry.Point2D;


/**
 * for manage movement of the entity.
 */
public interface Movement extends EntityComponent {

    /**
     * method that permit to entity to move.
     * @param direction
     *          the direction where the entity will move.
     */
    void move(Point2D direction);

    /**
     * Return the State.
     * @return
     *      the actual State.
     */
    State getState();

    /**
     * Update the component and applies the movement.
     * @param dt
     *      time delta in seconds since the last update call.
     *
     */
    @Override
    void update(double dt);

    /**
     * get the speed.
     * @return speed.
     */
    double getSpeed();

    /**
     * set the new speed.
     * @param speed
     *          speed to set.
     */
    void setSpeed(double speed);

    /**
     * Calculates the vector of the movement based on the direction.
     * @param direction
     *              Point that describe the direction where the entity will go.
     * @return Point2D
     *              calculated point.
     */
    Point2D calculateVector(Direction direction);

    /**
     * Enumeration for possible State that entity can have.
     */
    enum State implements EntityState {
        STABLE, WALKING, DYING
    }
}
