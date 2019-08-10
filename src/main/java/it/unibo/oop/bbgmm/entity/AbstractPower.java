package it.unibo.oop.bbgmm.entity;

/**
 * This is an abstract class for the power
 */
public abstract class AbstractPower implements Power{

    private boolean isActive;
    private PowerTag powerTag;
    private Entity player;

    public AbstractPower(final PowerTag powerTag){
        this.powerTag=powerTag;
    }

    @Override
    public void activate(final Entity player) {
        this.isActive = true;
        this.player=player;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public PowerTag getPowerTag() {
        return powerTag;
    }

    @Override
    public void deactivate() {
        this.isActive=false;
    }

    public Entity getPlayer() {
        return player;
    }
}
