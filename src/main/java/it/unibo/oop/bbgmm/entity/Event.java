package it.unibo.oop.bbgmm.entity;

/**
 * It represents an Event.
 * @param <T>
 *     the type of the event
 */
public interface Event<T> {

    /**
     * Register the {@link EventHandler} to the Event.
     * @param handler
     *      the handler to be registered.
     */
    void register(EventHandler<T> handler);

    /**
     * Deregister the {@link EventHandler} to the Event.
     * @param handler
     *      the handler to deregister.
     */
    void deregister(EventHandler<T> handler);
}
