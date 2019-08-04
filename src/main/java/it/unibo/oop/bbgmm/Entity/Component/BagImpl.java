package it.unibo.oop.bbgmm.Entity.Component;

import it.unibo.oop.bbgmm.Entity.Power;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of {@link Bag}.
 */

public final class BagImpl extends AbstractEntityComponent implements Bag {

    private int money;
    private List<Power> powers = new LinkedList<>();

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void addMoney(final int amount) {
        money += amount;
        System.out.println(money);
        //notifyChange();
    }

    @Override
    public void update(final double up) {
        final List<Power> inactivePowers = new LinkedList<>();
        this.powers.forEach(p -> {
            if(p.isActive())
            {
                p.update(up);
            } else {
                inactivePowers.add(p);
            }
        });
        powers.removeAll(inactivePowers);
    }

    @Override
    public void addPower(Power power) {
        this.powers.add(power);
    }

    @Override
    public List<Power> getPowers() {
        return this.powers;
    }

    //complete with a notification of the change
}
