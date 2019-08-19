package it.unibo.oop.bbgmm.entity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Represent an event which accept a {@link EventHandler} as an observer.
 * @param <T>
 *     the type of the argument
 */
public final class EventSource<T> implements Event<T> {

    private final BlockingQueue<EventHandler<T>> registeredHandlers;

    /**
     * {@link Event} constructor.
     */
    public EventSource() {
        registeredHandlers = new LinkedBlockingQueue<>();
    }

    @Override
    public void register(final EventHandler<T> handler) {
        this.registeredHandlers.add(handler);
    }

    @Override
    public void deregister(final EventHandler<T> handler) {
        this.registeredHandlers.remove(handler);
    }

    /**
     * Triggers the {@link Event}, all registered handlers will be notified.
     * @param argument
     *      the Event's argument
     */
    public void trigger(final T argument) {
        this.registeredHandlers.forEach(x -> x.handle(argument));
    }
}
