package it.unibo.oop.bbgmm.Entity;

import java.util.Set;

/**
 * Runs the game field.
 */
public interface GameField {

    /**
     * Update the field simulation.
     *
     * @param up
     *      Time to simulate, in seconds
     */
    void update(double up);

    /**
     * Adds an entity in the game field.
     *
     * @param entity
     *      The entity that is going to be added in the game field
     * @return the entity
     */
    Entity addEntity(Entity entity);
}
