package it.unibo.oop.bbgmm.Utilities;

import java.awt.*;

public class Resolution {
    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension d = tk.getScreenSize();
    private static int width = d.width/2;
    private static int height = (2*d.height)/3;

    public Resolution() {
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setSmallResolution(){
        width = d.width/2;
        height = (2*d.height)/3;
    }

    public static void setFullResolution(){
        width = d.width;
        height = d.height;
    }
}
