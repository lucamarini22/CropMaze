package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Utilities.Pair;
import java.util.stream.Stream;

public interface EntityBody extends EntityComponent{

    /**
     * @return the position
     */
    Pair<Integer, Integer> getPosition();

    /**
     *
     * @param speed
     *          the velocity of the entity
     */
    void setVelocity(int speed);

    int getVelocity();

    /**
     * get all {@link EntityBody} object colliding with this one
     * @return a Stream of the object that colliding with the entity
     */
    Stream<EntityBody> getContacts();

    /**
     *
     * @return true if can't cross the body because it's not solid
     */
    boolean notGoThrough();

}
