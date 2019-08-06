package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.GameFieldView;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.GameField;
import it.unibo.oop.bbgmm.Entity.PlayerStatistics;

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
     * @return the field model
     */
    GameField getGameField();


    /**
     * @return the game field view
     */
    GameFieldView getGameFieldView();

    /**
     * @return the set of entities controllers
     */
    Set<EntityController> getEntitiesControllers();

    /**
     * @return the player statistics
     */
    PlayerStatistics getPlayerStatistic();

    /**
     * Loads the entities in the field.
     */
    void initializeLevel();

}
