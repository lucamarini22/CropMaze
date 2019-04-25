package it.unibo.oop.bbgmm.Boundary;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Manuel
 *  Class used to load images from path.
 */

public class ImagesLoader {

    private final Map<String, Image> images = new HashMap<>();

    /**
     * @param path
     * @return Image loaded from path
     * @throws IOException
     */
    public Image getImage(final String path) throws IOException {
        if (!images.containsKey(path)) {
            images.put(path, new Image(ImagesLoader.class.getResourceAsStream(System.getProperty("file.separator") + path)));
        }
        return images.get(path);
    }
}
