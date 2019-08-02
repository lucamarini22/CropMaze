package it.unibo.oop.bbgmm.Boundary;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;



public class StatusBarImpl implements StatusBar {

    private static final int PADDING = 20;
    private static final Image maxHealthBar = new Image("images/damageBar.png");
    private static final Image currentHealthBar = new Image("images/lifeBar.png");


    public StatusBarImpl(){

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
