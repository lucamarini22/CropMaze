package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.EntityBody;
import it.unibo.oop.bbgmm.entity.component.EntityComponent;

import java.util.Optional;

public interface Entity {

    /**
     *
     * @return the body of the entity
     */
    EntityBody getBody();

    /**
     * Get a component by its type
     *
     * @param component
     *      component that we want get
     * @return the component
     */
    <C extends EntityComponent> Optional<C> get(Class<C> component);

    /**
     * remove a component from the body of the entity
     * @param component to remove
     */
    void remove (EntityComponent component);

    /**
     * add a new component at the entity
     * @param component that will be added
     */
    void add(EntityComponent component);

    /**
     * used to synchronize the entities
     *
     * @param up time since last call
     */
    void update(double up);

    /**
     * destroy the entity
     */
    void destroy();

    /**
     * getter the death event
     * @return Event<DeathEvent>
     */
    Event<DeathEvent> getDeathEvent();

    /**
     * notify the death event
     */
    void removeEntity();
}