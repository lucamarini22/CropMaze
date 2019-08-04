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

    /**
     * Gets all the {@link Entity} in the game.
     *
     * @return all the entities
     */
    Set<Entity> getEntities();

    /**
     * Removes the {@link Entity} passed by argument.
     *
     * @param entity
     *      The {@link Entity} to remove
     */
    void removeEntity(Entity entity);

    /**
     * @return all the {@link Wall} present in the game field
     */
    Set<Entity> getWalls();

    /**
     * Destroy an {@link Entity} in the field.
     * @param event
     *      {@link Event} that triggers the destruction
     */
    void destroyEntity(DeathEvent event);
}
