package it.unibo.oop.bbgmm.Entity;

import javafx.geometry.Point2D;

/**
 * {@link Entity} spawner.
 */
public interface EntitySpawner {

    /**
     * SPawns an {@link Entity}.
     *
     * @param entityType
     *      The type of the {@link Entity}
     * @param position
     *      Position of the {@link Entity}
     * @return the {@link Entity} to spawn
     */
    Entity spawn(String entityType, Point2D position);

}
