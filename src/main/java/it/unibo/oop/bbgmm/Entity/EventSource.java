package it.unibo.oop.bbgmm.Entity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventSource<T> implements Event<T> {

    private final BlockingQueue<EventHandler<T>> registeredHandlers;

    public EventSource() {
        registeredHandlers = new LinkedBlockingQueue<>();
    }

    @Override
    public void register(EventHandler<T> handler) {
        this.registeredHandlers.add(handler);
    }

    @Override
    public void deregister(EventHandler<T> handler) {
        this.registeredHandlers.remove(handler);
    }

    public void trigger(final T argument) {
        this.registeredHandlers.forEach(x -> x.handle(argument));
    }
    
}