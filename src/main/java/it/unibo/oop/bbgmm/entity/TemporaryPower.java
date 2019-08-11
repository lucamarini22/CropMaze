package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.utilities.Temporary;

/**
 * This class represents a power that have a limited life time
 */
public abstract class TemporaryPower extends AbstractPower implements Temporary {

    private double time;
    private double timeout;

    public TemporaryPower(final double timeout, final PowerTag powerTag){
        super(powerTag);
        this.time=timeout;
        this.timeout=timeout;
    }

    @Override
    public void update(double dt) {
        if(this.time < 0) {
            this.deactivate();
        }
        else
        {
            this.time = this.time - dt;
        }
    }

    @Override
    public double getRemainingTimePercentage() {
        return this.time/this.timeout;
    }

    @Override
    public double getRemainingTime() {
        return this.time;
    }

    @Override
    public void addTime(double time) {
        this.time = this.time + time;
        this.timeout = this.timeout + time;
    }
}
