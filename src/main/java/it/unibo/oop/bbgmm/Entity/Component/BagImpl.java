package it.unibo.oop.bbgmm.Entity.Component;


/**
 * Implementation of {@link Bag}.
 */
public final class BagImpl extends AbstractEntityComponent implements Bag {

    private int money;

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void addMoney(final int amount) {
        money += amount;
        //notifyChange();
    }

    @Override
    public void update(final double up) {
        //
    }

    //complete with a notification of the change
}
