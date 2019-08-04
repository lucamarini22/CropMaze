package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Point2D;

public interface Brain extends EntityComponent {


    /**
     * Method used by aliens to find the player
     * @param player
     * @param alien
     * @return true if the alien in near to the player
     */
    //public boolean findPlayer(Entity player, Entity alien);

    /**
     * select a new direction for find the player
     */
    //public void checkDirection(Entity player, Entity alien);



}
