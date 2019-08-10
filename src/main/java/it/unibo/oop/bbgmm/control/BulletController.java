package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.BulletView;
import it.unibo.oop.bbgmm.boundary.ViewUtils;
import it.unibo.oop.bbgmm.entity.component.Life;
import it.unibo.oop.bbgmm.entity.Entity;

/**
 * Controller for the Bullet.
 */
public final class BulletController extends AliveEntityController {

    private final PlayerController playerController;

    /**
     * Constructor for BulletController.
     *
     * @param bullet
     *          The Bullet entity
     * @param bulletView
     *          The Bullet View
     * @param playerController
     *          The controller for the Player
     */
    public BulletController(final Entity bullet, final BulletView bulletView, final PlayerController playerController) {
        super(bullet, bulletView);
        this.playerController = playerController;
    }

    @Override
    public void update() {
        if (getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()) {
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        } else {
            getEntityView().deathView();
            this.playerController.removeBulletController(this);
        }
    }
}
