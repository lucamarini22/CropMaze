package it.unibo.oop.bbgmm.Utilities;

import java.awt.*;

/**
 * @author Manuel
 * Class used to set and get the resolution of the GameWindow
 */

public final class Resolution {
    public static final int SMALL_HEIGHT = 768;
    public static final int SMALL_WIDTH = 1024;
    private static final Toolkit TK = Toolkit.getDefaultToolkit();
    private static final Dimension D = TK.getScreenSize();
    private static int width = SMALL_WIDTH;
    private static int height = SMALL_HEIGHT;
    private static boolean fullScreen;

    public Resolution() {
        fullScreen=false;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setSmallResolution(){
        width = SMALL_WIDTH;
        height = SMALL_HEIGHT;
        fullScreen = false;
    }

    public static void setFullResolution(){
        width = D.width;
        height = D.height;
        fullScreen = true;
    }

    public static boolean isFullScreen(){return fullScreen;}
}
