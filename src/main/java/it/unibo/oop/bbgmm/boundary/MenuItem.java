package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.utilities.FontMakerUtil;
import it.unibo.oop.bbgmm.utilities.ResolutionUtil;
import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


/**
 * Creator of the Item.
 */
public class MenuItem extends HBox {

    private static final int RADIUS = 2;
    private final Text text;
    private Runnable script;

    /**
     * Constructor for MenuItem.
     * @param name
     *          The string shown
     */
    public MenuItem(final String name) {
        super();
        setAlignment(Pos.CENTER);
        this.text = new Text(name);
        FontMakerUtil.modifyFont(ResolutionUtil.isFullScreen());
        this.text.setFont(FontMakerUtil.getFont());
        this.text.setEffect(new GaussianBlur(RADIUS));
        getChildren().add(this.text);
        setActive(false);
    }

    /**
     *  Setter for the color of the item.
     *  It changes when the item is selected.
     *
     * @param b
     *          Boolean true or false
     */
    public final void setActive(final boolean b) {
        this.text.setFill(b ? Color.YELLOW : Color.FORESTGREEN);
    }

    /**
     * Setter for the code to execute when the item is activated.
     *
     * @param r
     *          The code that is run
     */
    public void setOnActivate(final Runnable r) {
        this.script = r;
    }

    /**
     * Method that activates the action of the item.
     */
    public void activate() {
        if (this.script != null) {
            this.script.run();
        }
    }

    /**
     * Setter used to underline the text.
     *
     * @param b
     *          Boolean true or false
     */
    public void setUnderline(final boolean b) {
        this.text.setUnderline(b);
    }

    /**
     * Setter for the font.
     *
     * @param size
     *          The size of the font
     */
    public void setFont(final double size) {
        this.text.setFont(FontMakerUtil.getSizedFont(size));
    }
}
