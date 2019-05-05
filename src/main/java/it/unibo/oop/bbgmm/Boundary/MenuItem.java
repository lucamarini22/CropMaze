package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Manuel
 * Utility class with methods used to get and set the Resolution of the screen
 */

public class MenuItem extends HBox {
    private static final Font FONT = Font.font("MS Gothic", FontWeight.BOLD, 70);

    private Text text;
    private Runnable script;

    public MenuItem(String name) {
        super(15);
        setAlignment(Pos.CENTER);
        text = new Text(name);
        text.setFont(FONT);
        text.setEffect(new GaussianBlur(2));
        getChildren().add(text);
        setActive(false);
    }

    /**
     *  Setter of the color of the text
     * @param b
     */
    public void setActive(boolean b) {
        text.setFill(b ? Color.YELLOW : Color.YELLOWGREEN);
    }

    /**
     * Setter for the code to execute when the item is activated
     * @param r
     */
    public void setOnActivate(Runnable r) {
        script = r;
    }

    /**
     * Method that activates the action of the item
     */
    public void activate() {
        if (script != null)
            script.run();
    }

    /**
     * Setter used to underline the text
     * @param b
     */
    public void setUnderline(boolean b){text.setUnderline(b);}
}
