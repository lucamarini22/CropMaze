package it.unibo.oop.bbgmm.entity.component;

/**
 * Models a timer.
 */
public final class TimerImpl implements Timer {
    private final double end;
    private double timeElapsed;

    /**
     * @param time
     *            The timer will be elapsed after the given time in seconds.
     */
    public TimerImpl(final double time) {
        end = time;
    }

    /**
     * Restarts the timer.
     */
    public void restart() {
        timeElapsed = 0;
    }

    /**
     * Update the timer.
     * @param delta
     *      The time elapsed.
     */
    public void update(final double delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Can't rewind a timer passing a negative delta");
        }
        timeElapsed += delta;
    }

    /**
     * @return True if the timer is not elapsed.
     */
    public boolean isElapsed() {
        return timeElapsed >= end;
    }
}
