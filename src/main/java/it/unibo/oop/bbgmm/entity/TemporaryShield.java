package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.Life;

/**
 * Create a shield around the player and makes it invulnerable
 */
public class TemporaryShield extends TemporaryPower {

    private static final PowerTag powerTag = PowerTag.SHIELD;

    public TemporaryShield(double timeout) {
        super(timeout, powerTag);
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
