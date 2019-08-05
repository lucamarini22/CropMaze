package it.unibo.oop.bbgmm.Entity;
import it.unibo.oop.bbgmm.Entity.Component.EntityComponent;
import javafx.geometry.Point2D;


/**
 * for manage movement of the entity
 */
public interface Movement extends EntityComponent {

    /**
     *
     * @param direction
     *          the direction where the entity will move
     */
    void move(Point2D direction);

    /**
     *
     * @return Movement state
     */
    State getState();

    /**
     * Update the component and applies the movement
     * @param dt
     *      time delta in seconds since the last update call
     *
     */
    @Override
    void update(double dt);

    double getSpeed();

    void setSpeed(double speed);



    enum State implements EntityState{
        STABLE, WALKING, DYING
    }
}
