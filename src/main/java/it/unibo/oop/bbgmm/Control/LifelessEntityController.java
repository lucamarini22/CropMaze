package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.LifelessEntityView;
import it.unibo.oop.bbgmm.Entity.Entity;


<<<<<<< HEAD
public class LifelessEntityController extends AbstractLifelessEntityController<LifelessEntityView> {
=======
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

>>>>>>> 02dff5e65a74fd2e8fe0e3398b05f9ab92282505

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
