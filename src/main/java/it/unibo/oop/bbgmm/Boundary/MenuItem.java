package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Pos;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItem extends HBox {
    private static final Font FONT = Font.font("MS Gothic", FontWeight.BOLD, 65);

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

    public void setActive(boolean b) {
        text.setFill(b ? Color.YELLOW : Color.YELLOWGREEN);
    }

    public void setOnActivate(Runnable r) {
        script = r;
    }

    public void activate() {
        if (script != null)
            script.run();
    }

    public void setUnderline(boolean b){text.setUnderline(b);}
}
