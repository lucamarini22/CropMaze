package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.PowerTag;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a view for PowerUp
 */

public class PowerUpView extends AbstractEntityView implements LifelessEntityView {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private static final String SHIELD = "";
    private static final String DOUBLEDAMAGE = "";
    private static final String DOUBLESPEED = "";

    /**
     * @param dimension instance where entity view is collocated
     * @param group
     */
    public PowerUpView(Dimension2D dimension, Group group, PowerTag type) {
        super(dimension, group);
        if(type == PowerTag.SHIELD){
            getView().setImage(new Image(SHIELD));
        } else if(type == PowerTag.DOUBLEDAMAGE) {
            getView().setImage(new Image(DOUBLEDAMAGE));
        } else if(type == PowerTag.DOUBLESPEED) {
            getView().setImage((new Image(DOUBLESPEED)));
        }
    }
}