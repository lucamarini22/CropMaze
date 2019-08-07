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
 * Creator of the Item.
 */
public class MenuItem extends HBox {

    private final Text text;
    private Runnable script;

    public MenuItem(final String name) {
        super();
        setAlignment(Pos.CENTER);
        text = new Text(name);
        FontMaker.modifyFont(Resolution.isFullScreen());
        text.setFont(FontMaker.getFont());
        text.setEffect(new GaussianBlur(2));
        getChildren().add(text);
        setActive(false);
    }

    /**
     *  Setter for the color of the item.
     *  It changes when the item is selected.
     *
     * @param b
     *          Boolean true or false
     */
    public void setActive(final boolean b) {
        text.setFill(b ? Color.YELLOW : Color.FORESTGREEN);
    }

    /**
     * Setter for the code to execute when the item is activated.
     *
     * @param r
     *          The code that is run
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
     *
     * @param b
     *          Boolean true or false
     */
    public void setUnderline(final boolean b) {
        text.setUnderline(b);
    }

    /**
     * Setter for the font.
     *
     * @param size
     *          The size of the font
     */
    public void setFont(final int size){
        text.setFont(FontMaker.getSizedFont(size));
    }
}
