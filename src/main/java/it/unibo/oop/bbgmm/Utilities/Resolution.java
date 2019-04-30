package it.unibo.oop.bbgmm.Utilities;

import java.awt.*;

public class Resolution {
    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension d = tk.getScreenSize();
    private static int width = 1024;
    private static int height = 768;

    public Resolution() {
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setSmallResolution(){
        width = 1024;
        height = 768;
    }

    public static void setFullResolution(){
        width = d.width;
        height = d.height;
    }
}
