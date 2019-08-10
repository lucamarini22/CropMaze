package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

import static it.unibo.oop.bbgmm.Boundary.Music.BULLET_SHOT;

/**
 * Class responsible for the view of the Bullet.
 */
public class BulletView extends AbstractAliveEntityView {

    private static final int WIDTH = 0, HEIGHT = 4;

    /**
     * Constructor for BulletView.
     *
     * @param group
     *          The group to which is added
     * @param direction
     *          The direction of the bullet
     * @param audioPlayer
     *          The audioPlayer
     */
    public BulletView(final Group group, final Direction direction, final AudioPlayer audioPlayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        setCurrentImage(direction);
        audioPlayer.playSound(BULLET_SHOT.getPath());
    }

    /**
     * Setter for the image.
     */
    private void setCurrentImage(final Direction direction) {
        switch (direction) {
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
