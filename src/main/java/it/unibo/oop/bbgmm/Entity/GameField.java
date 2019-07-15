package it.unibo.oop.bbgmm.Entity;

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
     *
     * @param entity
     *      The entity that is going to be added in the game field
     */
    void addEntity(Entity entity);
}
