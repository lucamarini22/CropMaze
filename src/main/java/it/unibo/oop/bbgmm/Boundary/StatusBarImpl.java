package it.unibo.oop.bbgmm.Boundary;

import javafx.scene.layout.HBox;

import java.awt.*;

public class StatusBarImpl implements StatusBar {

    private Graphics maxHealthBar;
    private Graphics currentHealthBar;


    public StatusBarImpl(){
        maxHealthBar.setColor(Color.red);
        currentHealthBar.setColor(Color.green);
    }
    @Override
    public void setMaxLifePoints(int maxLifePoints) {
        maxHealthBar.drawRect(0,0, maxLifePoints,0);
    }

    @Override
    public void setCurrentLifePoints(int currentLifePoints) {
        currentHealthBar.drawRect(0,0, currentLifePoints,0);
    }

    @Override
    public void setCoins(int coins) {

    }


}
