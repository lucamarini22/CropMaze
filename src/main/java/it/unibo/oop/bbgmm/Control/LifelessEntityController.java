package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.LifelessEntityView;
import it.unibo.oop.bbgmm.Entity.Entity;


/**
 * Base class for AbstractLifeEntityController
 */
public class LifelessEntityController extends AbstractLifelessEntityController<LifelessEntityView> {
    /**
     *
     * @param entity
     *             the object to control
     * @param entityView
     *              the object to update
     */
    public LifelessEntityController(final Entity entity, final LifelessEntityView entityView) {
        super(entity, entityView);
    }


}
