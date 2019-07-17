package it.unibo.oop.bbgmm.Entity.Component;

/**
 * A bag for an entity that contains coins.
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
}
