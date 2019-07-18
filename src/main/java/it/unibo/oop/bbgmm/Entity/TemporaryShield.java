package it.unibo.oop.bbgmm.Entity;

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
        //attivare invulnerabilità sul player
    }

    @Override
    public void deactivate() {
        super.deactivate();
        //disattivare invulnerabilità sul player
    }
}
