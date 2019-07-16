package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.BodyBuilder;

import java.util.function.Function;

/**
 * Runs the game field.
 */
public interface GameField {

    /**
     * Update the field simulation.
     *
     * @param up Time to simulate, in seconds
     */
    void update(double up);

    /**
     * Adds an entity in the game field.
     * @param entity
     *      The entity that is going to be added in the game field
     */
    Entity addEntity(Entity entity);
}
