package it.unibo.oop.bbgmm.Entity;

/**
 * This power gives double damage to the weapon's player
 */
public class TemporaryDoubleDamage extends TemporaryPower {

    private static final PowerTag powerTag = PowerTag.DOUBLEDAMAGE;

    public TemporaryDoubleDamage(double timeout) {
        super(timeout, powerTag);
    }

    @Override
    public void activate(Entity player) {
        super.activate(player);
        //attivare
    }

    @Override
    public void deactivate() {
        super.deactivate();
        //disattivare
    }
}
