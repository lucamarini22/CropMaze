package it.unibo.oop.bbgmm.entity;

/**
 * This power gives double speed to the player.
 */
public final class TemporaryDoubleSpeed extends TemporaryPower {

    private static final PowerTag POWER_TAG = PowerTag.DOUBLESPEED;

    /**
     * This constructor set the timeout.
     * @param timeout
     *      the duration of the power
     */
    public TemporaryDoubleSpeed(final double timeout) {
        super(timeout, POWER_TAG);
    }

    @Override
    public void activate(final Entity player) {
        super.activate(player);
        player.get(Movement.class).ifPresent(m -> m.setSpeed(m.getSpeed() * 2));
    }

    @Override
    public void deactivate() {
        super.deactivate();
        getPlayer().get(Movement.class).ifPresent(m -> m.setSpeed(m.getSpeed() / 2));
    }
}
