package it.unibo.oop.bbgmm.control;

import java.io.IOException;

/**
 * Interface for a map loader.
 * @param <T>
 *      Type of the map
 */
public interface MapLoader<T> {
    /**
     * Loads the map.
     * @return the map
     * @throws IOException
     *      if there is not the map or the file is wrong
     */
     T loadMap() throws IOException;
}
