package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Power;

import java.util.List;

/**
 * A bag for an entity that contains coins and powers.
 */
public interface Bag extends EntityComponent {
    /**
     * @return the value of the all collected coins.
     */
    int getMoney();

    /**
     * Adds money.
     *
     * @param amount
     *      amount of money to add
     */
    void addMoney(int amount);

    /**
     * Adds power.
     * @param power
     */

    void addPower(Power power);

    /**
     * Returns a list of powers.
     * @return
     */
    List<Power> getPowers();

}
