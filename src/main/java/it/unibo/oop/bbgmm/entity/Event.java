package it.unibo.oop.bbgmm.entity;

public interface Event<T> {

    void register(EventHandler<T> handler);

    void deregister(EventHandler<T> handler);
}
