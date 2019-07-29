package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.LifelessEntityView;
import it.unibo.oop.bbgmm.Entity.Entity;


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
