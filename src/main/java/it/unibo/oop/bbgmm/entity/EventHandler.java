package it.unibo.oop.bbgmm.entity;

/**
 * It is responsible of handing an {@link Event}.
 * @param <T>
 */
public interface EventHandler<T> {

    /**
     * Handles the event.
     * @param argument
     *      the parameter of the event
     */
    void handle(T argument);
}
