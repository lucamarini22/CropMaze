package it.unibo.oop.bbgmm.Entity;

public interface Event<T> {

    void register(EventHandler<T> handler);

    void deregister(EventHandler<T> handler);
}
