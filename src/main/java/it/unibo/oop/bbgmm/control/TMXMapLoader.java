package it.unibo.oop.bbgmm.control;

import com.google.common.io.Files;
import it.unibo.oop.bbgmm.utilities.ZipExtractorUtil;
import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;

import java.io.File;
import java.io.InputStream;

/**
 * Implementation of {@link MapLoader} with a ".tmx" map loader.
 */
public final class TMXMapLoader implements  MapLoader {
    private static final String MAP_PATH = "/images/Map/CropMazeMap.zip";
    private static final String MAP_NAME = "CropMazeMap.tmx";
    private Map map;

    @Override
    public Map loadMap() throws Exception {
        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream(MAP_PATH)) {
            ZipExtractorUtil.extract(is, tempDir);
            this.map = new TMXMapReader().readMap(new File(tempDir, MAP_NAME).getAbsolutePath());
        } catch (final Exception e) {
            System.out.println("ERROR: Can't load map\n");
        }
        return this.map;
    }
}
