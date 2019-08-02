package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.PowerTag;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a view for PowerUp
 */

public class PowerUpView extends AbstractEntityView implements LifelessEntityView{

    public PowerUpView(Group group, Dimension2D dimension, PowerTag tag) {
        super(group, dimension);
        setImage(tag);
    }

    private void setImage(PowerTag tag){
        switch (tag){

            case DOUBLEDAMAGE:
                getView().setImage(new Image("images/shield.png"));
            case SHIELD:
                getView().setImage(new Image("images/shield.png"));
            case DOUBLESPEED:
                getView().setImage(new Image("images/shield.png"));
        }
    }
}