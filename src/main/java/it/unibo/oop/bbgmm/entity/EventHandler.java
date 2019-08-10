package it.unibo.oop.bbgmm.entity;

public interface EventHandler<T> {

    void handle(T argument);
}
