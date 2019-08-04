package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Point2D;

/**
 * automatic pilot the entity
 */
public interface Brain extends EntityComponent {


    /**
     * Method to move random the owner
     */
    void randomMovement();



}
