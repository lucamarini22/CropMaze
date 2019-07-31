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
     *
     * @return
     */
    double getSpeed();


    void setSpeed(double speed);



    enum State implements EntityState{
        STABLE, WALKING, DYING;
    }
}
