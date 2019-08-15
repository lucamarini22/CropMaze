package it.unibo.oop.bbgmm.entity;

/**
 * This is an abstract class for the power.
 */
public abstract class AbstractPower implements Power {

    private boolean isActive;
    private PowerTag powerTag;
    private Entity player;

    /**
     * Constructor for AbstractPower.
     *
     * @param powerTag
     *          The PowerTag
     */
    public AbstractPower(final PowerTag powerTag) {
        this.powerTag = powerTag;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void activate(final Entity player) {
        this.isActive = true;
        this.player = player;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return this.isActive;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public PowerTag getPowerTag() {
        return this.powerTag;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void deactivate() {
        this.isActive = false;
    }

    /**
     * Getter for the player.
     *
     * @return Entity
     *          The player
     */
    public Entity getPlayer() {
        return this.player;
    }
}
