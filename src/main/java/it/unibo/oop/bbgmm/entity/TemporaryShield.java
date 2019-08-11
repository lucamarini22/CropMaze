package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.Life;

/**
 * Create a shield around the player and makes it invulnerable.
 */
public final class TemporaryShield extends TemporaryPower {

    private static final PowerTag POWER_TAG = PowerTag.SHIELD;

    /**
     * This constructor set the timeout.
     * @param timeout
     *      the duration of the power
     */
    public TemporaryShield(final double timeout) {
        super(timeout, POWER_TAG);
    }

    @Override
    public void activate(final Entity player) {
        super.activate(player);
        player.get(Life.class).ifPresent(life ->
            life.setVulnerability(false));
    }

    @Override
    public void deactivate() {
        getPlayer().get(Life.class).ifPresent(life -> life.setVulnerability(true));
        super.deactivate();
    }
}
