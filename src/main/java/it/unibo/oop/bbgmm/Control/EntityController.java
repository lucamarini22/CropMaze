package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.Entity;

/**
 * Translates view input to model input and update the view
 */
public interface EntityController extends Entity {

    /**
     * synchronize the entity controller
     */
    void update();
}
