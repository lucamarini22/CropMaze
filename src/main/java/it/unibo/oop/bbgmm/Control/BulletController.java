package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.BulletView;
import it.unibo.oop.bbgmm.Boundary.ViewUtils;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import it.unibo.oop.bbgmm.Entity.Entity;

/**
 * Controller for the Bullet.
 */
public final class BulletController extends AliveEntityController {

    private final PlayerController playerController;

    /**
     * Constructor for BulletController.
     *
     * @param bullet
     *          The Bullet Entity
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
