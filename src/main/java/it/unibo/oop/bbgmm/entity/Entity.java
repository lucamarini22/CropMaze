package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.EntityBody;
import it.unibo.oop.bbgmm.entity.component.EntityComponent;

import java.util.Optional;

/**
 * Base class for Entity.
 */
public interface Entity {

    /**
     *
     * @return the body of the entity.
     */
    EntityBody getBody();

    /**
     * Get a component by his type.
     * @param component
     *          component that whe want get.
     * @param <C>
     *          extension of Component
     * @return
     *          the component.
     */
    <C extends EntityComponent> Optional<C> get(Class<C> component);

    /**
     * remove a component from the body of the entity.
     * @param component to remove.
     */
    void remove(EntityComponent component);

    /**
     * add a new component at the entity.
     * @param component that will be added.
     */
    void add(EntityComponent component);

    /**
     * used to coordinate events.
     * @param up
     *         time slice to do update.
     */
    void update(double up);

    /**
     * destroy the entity.
     */
    void destroy();

    /**
     * getter the death event.
     * @return Event<DeathEvent>.
     */
    Event<DeathEvent> getDeathEvent();

    /**
     * notify the death event.
     */
    void removeEntity();
}
