package it.unibo.oop.bbgmm.Entity;

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
        //attivare double speed sul player
    }

    @Override
    public void deactivate() {
        super.deactivate();
        //disattivare double speed sul player
    }
}
