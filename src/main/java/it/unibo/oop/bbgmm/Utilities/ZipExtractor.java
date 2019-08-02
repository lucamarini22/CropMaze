package it.unibo.oop.bbgmm.Utilities;

import com.google.common.io.ByteStreams;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Utils class used to extraxt files from a zip folder
 */
public final class ZipExtractor {

    /**
     * Method used to extract the file from the zip
     *
     * @param inputStream
     * @param directory
     * @throws IOException
     */
    public static void extract(final InputStream inputStream, final File directory) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(inputStream)) {
            for (ZipEntry entry = zis.getNextEntry(); entry != null; entry = zis.getNextEntry()) {
                if (!entry.isDirectory()) {
                    final File dest = new File(directory, entry.getName());
                    final File parentDir = dest.getParentFile();
                    if (!parentDir.exists() && !parentDir.mkdirs() && !parentDir.isDirectory()) {
                        throw new IOException("Couldn't create parent directory " + parentDir);
                    }
                    try (OutputStream fos = new BufferedOutputStream(new FileOutputStream(dest))) {
                        if (ByteStreams.copy(zis, fos) < 0) {
                            throw new IOException("This is not possible and it's here to make checkstyle happy");
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }
}
