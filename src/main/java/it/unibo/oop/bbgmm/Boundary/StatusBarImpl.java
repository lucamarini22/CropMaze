package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.awt.image.BufferedImage;


public final class StatusBarImpl implements StatusBarScreen {

    private static final int PADDING = 20;
    private final HBox statusBox = new HBox();
    ProgressBar pb = new ProgressBar(1);


    public StatusBarImpl(){
        pb.setStyle("-fx-accent: green");
        pb.setPrefWidth(500);
        pb.setPrefHeight(100);

        statusBox.getChildren().add(pb);
    }

    @Override
    public Node getStatusBox() {
        return this.statusBox;
    }

    @Override
    public void setMaxLifePoints(int maxLifePoints) {

    }



    @Override
    public void setCurrentLifePoints(int currentLifePoints) {

    }

    @Override
    public void setCoins(int coins) {

    }


}
