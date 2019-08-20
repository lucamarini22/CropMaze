package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

import static it.unibo.oop.bbgmm.boundary.Music.BULLET_SHOT;

/**
 * Class responsible for the view of the Bullet.
 */
public class BulletView extends AbstractAliveEntityView {

    private static final int WIDTH = 33, HEIGHT = 33;

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
     *
     * {@inheritDoc}
     */
    @Override
    public void setImage(final Image image) {
        getView().setImage(image);
        getView().setFitWidth(this.getDimension().getWidth());
        getView().setPreserveRatio(true);
    }

    /**
     * Setter for the image.
     */
    private void setCurrentImage(final Direction direction) {
        switch (direction) {
            case EAST: setImage(new Image("images/entities/BulletRight.png"));
                        break;
            case WEST: setImage(new Image("images/entities/BulletLeft.png"));
                        break;
            case NORTH: setImage(new Image("images/entities/BulletUp.png"));
                        break;
            case SOUTH: setImage(new Image("images/entities/BulletDown.png"));
                        break;
            default: break;
        }
    }
}
