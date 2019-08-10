package it.unibo.oop.bbgmm.utilities;


import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Class used to set and get the resolution of the GameWindow.
 */
public final class ResolutionUtil {
    public static final double SMALL_HEIGHT = 768;
    public static final double SMALL_WIDTH = 1024;
    private static final Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
    private static double width = SMALL_WIDTH;
    private static double height = SMALL_HEIGHT;
    private static boolean fullScreen = false;

    /**
     * Private constructor for ResolutionUtil.
     */
    private ResolutionUtil() {
    }

    /**
     * Getter for the Height.
     *
     * @return int
     *          The height
     */
    public static double getHeight() {
        return height;
    }

    /**
     * Getter for the Width.
     *
     * @return int
     *          The width
     */
    public static double getWidth() {
        return width;
    }

    /**
     * Setter the height and the width for the small resolution.
     */
    public static void setSmallResolution() {
        width = SMALL_WIDTH;
        height = SMALL_HEIGHT;
        fullScreen = false;
    }

    /**
     * Setter the height and the width for the full resolution.
     */
    public static void setFullResolution() {
        width = D.getWidth();
        height = D.getHeight();
        fullScreen = true;
    }

    /**
     * Returns true if the window is fullscreen.
     *
     * @return boolean
     *          True if fullscreen
     */
    public static boolean isFullScreen() {
        return fullScreen;
    }
}
