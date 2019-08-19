package it.unibo.oop.bbgmm.entity;

/**
 * This is an abstract class for the power.
 */
public abstract class AbstractPower implements Power {

    private boolean active;
    private final PowerTag powerTag;
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
        this.active = true;
        this.player = player;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public boolean isActive() {
        return this.active;
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
        this.active = false;
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
