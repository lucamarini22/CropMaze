package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.component.EntityBody;
import it.unibo.oop.bbgmm.entity.component.EntityComponent;
import it.unibo.oop.bbgmm.utilities.ComponentsContainerImpl;

import java.util.Optional;

/**
 * base class for entity type.
 */

public abstract class AbstractEntity implements Entity {
    private final EntityBody body;
    private final ComponentsContainerImpl<EntityComponent> components = new ComponentsContainerImpl<>(EntityComponent.class);
    private final EventSource<DeathEvent> deathEvent;
    /**
     * constructor for Abstract entity.
     * @param body
     *          the related body.
     */
    public AbstractEntity(final EntityBody body) {
        this.body = body;
        body.attach(this);
        this.deathEvent = new EventSource<>();
    }

    @Override
    public final EntityBody getBody() {
        return body;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void update(final double up) {
        updateComponents(up);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void destroy() {
        components.forEach(this::remove);
        this.remove(body);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final <C extends EntityComponent> Optional<C> get(final Class<C> component) {
        return components.get(component);
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void remove(final EntityComponent component) {
        components.remove(component);
        component.detach();
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void add(final EntityComponent component) {
        components.put(component);
        component.attach(this);
    }

    /**
     * Calls update on the component.
     * @param up
     *          Time for the update since last call.
     */
    protected void updateComponents(final double up) {
        components.forEach(c -> c.update(up));
    }

    /**
     * Method that get death event.
     * @return death event
     */
    public Event<DeathEvent> getDeathEvent() {
        return this.deathEvent;
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void removeEntity() {
        this.deathEvent.trigger(new DeathEvent(this));
    }
}
