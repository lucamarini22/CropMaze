package it.unibo.oop.bbgmm.Entity.Component;

public class TimerImpl {
    private final double end;
    private double timeElapsed;

    /**
     * @param time
     *            The timer will be elapsed after the given time in seconds.
     */
    public TimerImpl(final double time) {
        end = time;
    }


    public void restart() {
        timeElapsed = 0;
    }


    public void update(final double delta) {
        if (delta < 0) {
            throw new IllegalArgumentException("Can't rewind a timer passing a negative delta");
        }
        timeElapsed += delta;
    }


    public boolean isElapsed() {
        return timeElapsed >= end;
    }
}