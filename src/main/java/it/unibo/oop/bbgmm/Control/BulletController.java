package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.BulletView;
import it.unibo.oop.bbgmm.Boundary.ViewUtils;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import it.unibo.oop.bbgmm.Entity.Entity;

public class BulletController extends AliveEntityController{

    private final PlayerController  playerController;

    public BulletController(final Entity bullet, final BulletView bulletView, final PlayerController playerController) {
        super(bullet,bulletView);
        this.playerController = playerController;
    }

    @Override
    public void update(){
        if(getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()){
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        } else {
            getEntityView().deathView();
            playerController.removeBulletController(this);
        }
    }
}
