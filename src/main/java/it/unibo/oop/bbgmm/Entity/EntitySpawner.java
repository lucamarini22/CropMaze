package it.unibo.oop.bbgmm.Entity;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * {@link Entity} spawner.
 * It spawns entities and calculates the number of enemies to spawn.
 */
public interface EntitySpawner {

    /**
     * Spawns an {@link Entity}.
     *
     * @param entityType
     *      The type of the {@link Entity}
     * @param position
     *      Position of the {@link Entity}
     * @return the {@link Entity} to spawn
     */
    Entity spawn(String entityType, Point2D position);

    /**
     * Spawns an {@link Entity} that needs a dimension.
     *
     * @param position
     *      Position of the {@link Entity}
     * @param dimension
     *      Dimension of the {@link Entity}
     * @return the {@link Entity} to spawn
     */
    Entity spawn(Point2D position, Dimension2D dimension);


    // Levels start from one

    /**
     * @param currentLevel
     *      The number of the current level
     * @return the numbers of enemies to spawn
     */
    int getEnemiesNumber(int currentLevel);
}
