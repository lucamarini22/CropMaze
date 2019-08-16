package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.EntityType;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a view for PowerUp.
 */
public class PowerUpView extends AbstractEntityView implements LifelessEntityView {
    private static final int WIDTH = 72, HEIGHT = 97;
    private final AudioPlayer audioPlayer;

    /**
     * Creates a new PowerUpView.
     * @param group
     *      the group to be added
     * @param tag
     *      the entity type tag of the power
     * @param audioPlayer
     *      the audioPlayer
     */
    public PowerUpView(final Group group, final EntityType tag, final AudioPlayer audioPlayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.setImage(tag);
        this.audioPlayer = audioPlayer;
    }

    private void setImage(final EntityType tag) {
        switch (tag) {
            case DOUBLE_SPEED:
                getView().setImage(new Image("/images/wood_case_speed.png"));
                break;
            case SHIELD:
                getView().setImage(new Image("/images/wood_case_shield.png"));
                break;
            case DOUBLE_DAMAGE:
                getView().setImage(new Image("/images/wood_case_damage.png"));
                break;
                default:
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void playDeathSound() {
        this.audioPlayer.playSound(Music.OPEN_BOX.getPath());
    }
}
