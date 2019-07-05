package it.unibo.oop.bbgmm.Entity;

public interface EventHandler<T> {

    void handle(T argument);
}
