package it.unibo.oop.bbgmm.control;

/**
 * Interface for a map loader.
 * @param <T>
 *      Type of the map
 */
public interface MapLoader<T> {
    /**
     * Loads the map.
     * @return the map
     * @throws Exception
     *      if there is not the map or the file is wrong
     */
     T loadMap() throws Exception;
}
