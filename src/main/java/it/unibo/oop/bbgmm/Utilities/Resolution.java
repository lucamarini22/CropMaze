package it.unibo.oop.bbgmm.Utilities;

import java.awt.*;

/**
 * Class used to set and get the resolution of the GameWindow.
 */

public final class Resolution {
    public static final int SMALL_HEIGHT = 768;
    public static final int SMALL_WIDTH = 1024;
    private static final Toolkit TK = Toolkit.getDefaultToolkit();
    private static final Dimension D = TK.getScreenSize();
    private static int width = SMALL_WIDTH;
    private static int height = SMALL_HEIGHT;
    private static boolean fullScreen = false;

    /**
     * Getter for the Height.
     *
     * @return int
     *          The height
     */
    public static int getHeight() {
        return height;
    }

    /**
     * Getter for the Width.
     *
     * @return int
     *          The width
     */
    public static int getWidth() {
        return width;
    }

    /**
     * Setter the height and the width for the small resolution.
     */
    public static void setSmallResolution(){
        width = SMALL_WIDTH;
        height = SMALL_HEIGHT;
        fullScreen = false;
    }

    /**
     * Setter the height and the width for the full resolution.
     */
    public static void setFullResolution(){
        width = D.width;
        height = D.height;
        fullScreen = true;
    }

    /**
     * Returns if the window is fullscreen.
     *
     * @return boolean
     *          True if fullscreen
     */
    public static boolean isFullScreen(){return fullScreen;}
}
