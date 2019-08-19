package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Power;

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
     *      {@link Power} instance
     */
    void addPower(Power power);

    /**
     * @return list of powers
     */
    List<Power> getPowers();

}
