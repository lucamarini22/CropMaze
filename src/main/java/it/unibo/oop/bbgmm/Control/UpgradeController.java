package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.UpgradeType;

public interface UpgradeController {

    /**
     * Method uset to update the view
     */
    void updateView();

    /**
     * Upgrade the player
     * @param type
     *       the upgrade type
     */
    void upgradePlayer(UpgradeType type);
}
