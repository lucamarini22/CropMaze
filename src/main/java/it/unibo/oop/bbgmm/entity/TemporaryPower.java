package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.utilities.Temporary;

/**
 * This class represents a power that have a limited life time.
 */
public abstract class TemporaryPower extends AbstractPower implements Temporary {

    private double time;
    private double timeout;

    /**
     * This constructor set the timeout.
     * @param timeout
     *      the duration of the power
     * @param powerTag
     *      the power tag
     */
    public TemporaryPower(final double timeout, final PowerTag powerTag) {
        super(powerTag);
        this.time = timeout;
        this.timeout = timeout;
    }

    /**
     * It must be called in extended method.
     */
    @Override
    public void update(final double dt) {
        if (this.time < 0) {
            this.deactivate();
        } else {
            this.time = this.time - dt;
        }
    }

    @Override
    public final double getRemainingTime() {
        return this.time;
    }

    @Override
    public final void addTime(final double time) {
        this.time = this.time + time;
        this.timeout = this.timeout + time;
    }
}
