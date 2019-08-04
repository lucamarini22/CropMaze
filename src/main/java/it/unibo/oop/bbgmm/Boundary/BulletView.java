package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

import static it.unibo.oop.bbgmm.Boundary.Music.BULLET_SHOT;

public class BulletView extends AbstractAliveEntityView{

    private static final int WIDTH = 0, HEIGHT = 4;
    private final Direction direction;

    public BulletView(final Group group, final Direction direction, final AudioPlayer audioPlayer) {
        super(group, new Dimension2D(WIDTH,HEIGHT));
        this.direction = direction;
        setCurrentImage();
        audioPlayer.playSound(BULLET_SHOT.getPath());
    }

    private void setCurrentImage(){
        switch(direction){
            case EAST: setImage(new Image("images/bullets/BulletRight.png"));
                        break;
            case WEST: setImage(new Image("images/bullets/BulletLeft.png"));
                        break;
            case NORTH: setImage(new Image("images/bullets/BulletUp.png"));
                        break;
            case SOUTH: setImage(new Image("images/bullets/BulletDown.png"));
                        break;
        }
    }
}
