package it.unibo.oop.bbgmm.control;

import com.google.common.io.Files;
import it.unibo.oop.bbgmm.utilities.ZipExtractorUtil;
import org.mapeditor.core.Map;
import org.mapeditor.io.TMXMapReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation of {@link MapLoader} with a ".tmx" map loader.
 */
public final class TMXMapLoader implements  MapLoader {
    private static final String MAP_PATH = "/images/Map/CropMazeMap.zip";
    private static final String MAP_NAME = "CropMazeMap.tmx";

    @Override
    public Map loadMap() throws IOException {
        Map map;
        final File tempDir = Files.createTempDir();
        try (InputStream is = getClass().getResourceAsStream(MAP_PATH)) {
            ZipExtractorUtil.extract(is, tempDir);
            map = new TMXMapReader().readMap(new File(tempDir, MAP_NAME).getAbsolutePath());
        } catch (final IOException e) {
            throw new IOException("ERROR: Can't load map\n");
        } catch (final Exception e) {
            throw new IllegalArgumentException("ERROR: the file doesn't exist\n");
        }
        return map;
    }
}
