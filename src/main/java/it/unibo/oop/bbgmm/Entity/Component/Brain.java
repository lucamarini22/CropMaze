package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Point2D;

/**
 * automatic pilot the entity
 */
public interface Brain extends EntityComponent {


    /**
     * Method used by the alien to follow the main
     */
    void followPlayer();



}
