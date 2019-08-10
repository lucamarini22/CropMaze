package it.unibo.oop.bbgmm.utilities;

import javafx.scene.text.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Optional;

/**
 * Class used to create the right font to use based on the dimension of the GameWindow.
 */
public final class FontMakerUtil {

    private static final Dimension D = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String FONT_URL = ClassLoader.getSystemResource("font.ttf").toExternalForm();
    private static final int PROPORTION_VALUE = 15;
    private static final double FULL = D.getWidth();
    private static final int SMALL_FONT = 70;
    private static final int WINNER_FONT = 100;
    private static double currentDimension;
    private static Font font;
    private static Optional<Double> fullFont = Optional.empty();

    /**
     * Private constructor for FontMakerUtil.
     */
    private FontMakerUtil() {
    }

    /**
     * Modifies the font dimension.
     *
     * @param fullscreen
     *          Indicates if the window is fullscreen
     */
    public static void modifyFont(final boolean fullscreen) {
        if (fullscreen) {
            if (!fullFont.isPresent()) {
                currentDimension = FULL / PROPORTION_VALUE;
                fullFont = Optional.of(currentDimension);
            }
            font = Font.loadFont(FONT_URL, fullFont.get());
        } else {
            font = Font.loadFont(FONT_URL, SMALL_FONT);
            currentDimension = SMALL_FONT;
        }
    }

    /**
     * Getter for the font.
     *
     * @return Font
     *          The font to use
     */
    public static Font getFont() {
        return font;
    }

    /**
     * Getter for a sized font.
     *
     * @param size
     *          The dimension of the font
     * @return Font
     *          The font to use
     */
    public static Font getSizedFont(final double size) {
        return Font.loadFont(FONT_URL, size);
    }

    /**
     *  * Returns a plus sized font.
     *
     * @return Font
     *          The font to use
     */
    public static Font getFontWinner() {
        return Font.loadFont(FONT_URL, currentDimension * WINNER_FONT / SMALL_FONT);
    }
}
