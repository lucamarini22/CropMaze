package it.unibo.oop.bbgmm.entity;

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
        player.get(Movement.class).ifPresent( m -> m.setSpeed(m.getSpeed() * 2));
    }

    @Override
    public void deactivate() {
        super.deactivate();
        getPlayer().get(Movement.class).ifPresent(m -> m.setSpeed(m.getSpeed() / 2));
    }
}
