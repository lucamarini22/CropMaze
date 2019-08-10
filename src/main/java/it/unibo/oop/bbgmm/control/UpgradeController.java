package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.entity.UpgradeType;


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
