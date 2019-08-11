package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.LifelessEntityView;
import it.unibo.oop.bbgmm.entity.Entity;


/**
 * Base class for AbstractLifeEntityController
 */
public class LifelessEntityController extends AbstractLifelessEntityController<LifelessEntityView> {

        /**
         * @param entity
         *            The {@link Entity} object to control.
         * @param entityView
         *            The {@link LifelessEntityView} object to update.
         */
        public LifelessEntityController(final Entity entity, final LifelessEntityView entityView) {
            super(entity, entityView);
        }

}
