package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.GameFieldView;
import it.unibo.oop.bbgmm.Entity.Entity;

import java.util.Set;

/**
 * Interface for controlling a level.
 */
public interface Level {
    /**
     * @return the player
     */
    Entity getPlayer();
        /**
     * @return the game field view
     */
    GameFieldView getGameFieldView();

    /**
     * @return the set of entities controllers
     */
    Set<EntityController> getEntitiesControllers();

    /**
     * Loads the entities in the field.
     */
    void initializeLevel();

}
