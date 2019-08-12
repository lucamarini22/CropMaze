package it.unibo.oop.bbgmm.control;

/**
 * Interface for a map loader.
 */
public interface MapLoader<T> {
    /**
     * Loads the map.
     */
     T loadMap() throws Exception;
}
