package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.GameField;

public interface Level {
    /**
     * @return the player
     */
    Entity getPlayer();

    /**
     * @return the field model
     */
    GameField getGameField();

}
