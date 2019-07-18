package it.unibo.oop.bbgmm.Utilities;

import it.unibo.oop.bbgmm.Entity.Event;

/**
 *This interface represents an object which have a limited life time
 **/
public interface Temporary {

    /**
     * Returns a double between 0 and 1 that represents the percentage of remaining time.
     * @return
     *      the percentage of remaining time.
     */
    double getRemainingTimePercentage();
    /**
     * Returns a double that represents the time left.
     * @return
     *      the remaining time.
     */
    double getRemainingTime();
    /**
     * Give more time to the power.
     * @param time
     *      the time to be added to the power.
     */
    void addTime(double time);
}
