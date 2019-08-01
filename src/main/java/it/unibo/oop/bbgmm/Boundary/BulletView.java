package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class BulletView extends AbstractAliveEntityView{

    private final Direction direction;

    public BulletView(final Group group, final Dimension2D dimension, final Direction direction) {
        super(group, dimension);
        this.direction = direction;
        setCurrentImage();
    }

    private void setCurrentImage(){
        switch(direction){
            case EAST: putAnimation(PossibleEntityState.STABLE,
                                    staticAnimation(new Image("images/bullets/BulletRight.png")));
                        break;
            case WEST: putAnimation(PossibleEntityState.STABLE,
                                    staticAnimation(new Image("images/bullets/BulletLeft.png")));
                        break;
            case NORTH: putAnimation(PossibleEntityState.STABLE,
                                     staticAnimation(new Image("images/bullets/BulletUp.png")));
                        break;
            case SOUTH: putAnimation(PossibleEntityState.STABLE,
                                     staticAnimation(new Image("images/bullets/BulletDown.png")));
                        break;
        }
    }
}
