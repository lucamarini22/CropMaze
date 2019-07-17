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
     * Returns a void event that triggers when time is finished.
     * @return
     *      the timeout event
     */
    Event<Void> getTimeOutEvent();
}
