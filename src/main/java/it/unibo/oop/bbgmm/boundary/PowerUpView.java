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

    /**
     * Creates a new PowerUpView.
     * @param group
     *      the group to be added
     * @param tag
     *      the entity type tag of the power
     */
    public PowerUpView(final Group group, final EntityType tag) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.setImage(tag);
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
}
