package it.unibo.oop.bbgmm.Utilities;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.awt.*;
import java.util.Optional;

/**
 * Class used to create the rigth font to use based on the dimension of the GameWindow
 */
public class FontMaker {

    private static final Toolkit TK = Toolkit.getDefaultToolkit();
    private static final Dimension D = TK.getScreenSize();
    private static final int SMALL = 768;
    private static final int FULL = D.height;
    private static final int SMALL_FONT = 70;
    private static Font font;
    private static Optional<Integer> fullFont = Optional.empty();

    public static void modifyFont(boolean fullscreen){
        if(fullscreen){
            if(!fullFont.isPresent()){
                fullFont = Optional.of((SMALL_FONT*FULL)/SMALL);
            }
            font = Font.font("MS Gothic", FontWeight.BOLD, fullFont.get());
        }
        else{
            font = Font.font("MS Gothic", FontWeight.BOLD, SMALL_FONT);
        }
    }

    public static Font getFont() {
        return font;
    }
}