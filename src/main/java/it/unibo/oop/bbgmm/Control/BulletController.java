package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.BulletView;
import it.unibo.oop.bbgmm.Entity.Entity;

public class BulletController extends AliveEntityController{

    public BulletController(final Entity bullet, final BulletView bulletView) {
        super(bullet,bulletView);
    }

}
