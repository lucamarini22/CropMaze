package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Utilities.FontMaker;
import it.unibo.oop.bbgmm.Utilities.Resolution;
import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * @author Manuel
 * Utility class with methods used to get and set the Resolution of the screen
 */

public class MenuItem extends HBox {

    private Font usedFont;
    private final Text text;
    private Runnable script;

    public MenuItem(final String name) {
        super();
        setAlignment(Pos.CENTER);
        text = new Text(name);
        FontMaker.modifyFont(Resolution.isFullScreen());
        usedFont = FontMaker.getFont();
        text.setFont(usedFont);
        text.setEffect(new GaussianBlur(2));
        getChildren().add(text);
        setActive(false);
    }

    /**
     *  Setter of the color of the.
     *  It changes when the item is selected
     * @param b
     */
    public void setActive(final boolean b) {
        text.setFill(b ? Color.YELLOW : Color.FORESTGREEN);
    }

    /**
     * Setter for the code to execute when the item is activated.
     * @param r
     */
    public void setOnActivate(final Runnable r) {
        script = r;
    }

    /**
     * Method that activates the action of the item.
     */
    public void activate() {
        if (script != null) {
            script.run();
        }
    }

    /**
     * Setter used to underline the text.
     * @param b
     */
    public void setUnderline(final boolean b) {
        text.setUnderline(b);
    }
}
