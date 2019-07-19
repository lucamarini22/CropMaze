package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Entity;
import javafx.geometry.Point2D;

public interface Brain extends EntityComponent {

    /**
     * Move alien to player position
      * @param player
     * @param alien
     */
    public void moveToPlayer(Entity player, Entity alien);

    /**
     *
     * @param player
     * @param alien
     * @return true if the alien in near to the player
     */
    public boolean findPlayer(Entity player, Entity alien);


}
