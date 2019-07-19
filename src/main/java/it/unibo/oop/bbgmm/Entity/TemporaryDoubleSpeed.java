package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.Feet;

/**
 * This power gives double speed to the player
 */
public class TemporaryDoubleSpeed extends TemporaryPower {

    private static final PowerTag powerTag = PowerTag.DOUBLESPEED;

    public TemporaryDoubleSpeed(double timeout) {
        super(timeout, powerTag);
    }

    @Override
    public void activate(final Entity player) {
        super.activate(player);
        player.get(Feet.class).ifPresent(feet -> {
            double speed = feet.getSpeed();
            feet.setSpeed(speed * 2);
        });
    }

    @Override
    public void deactivate() {
        getPlayer().get(Feet.class).ifPresent(feet -> {
            double speed = feet.getSpeed();
            feet.setSpeed(speed/2);
        });
        super.deactivate();
    }
}
