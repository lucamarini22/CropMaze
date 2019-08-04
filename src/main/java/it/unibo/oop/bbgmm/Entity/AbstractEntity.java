package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Component.EntityBody;
import it.unibo.oop.bbgmm.Entity.Component.EntityComponent;
import it.unibo.oop.bbgmm.Utilities.ComponentsContainerImpl;
import it.unibo.oop.bbgmm.Utilities.ComponentsContainer;
import java.util.Optional;

/**
 * base class for entity type
 */

public abstract class AbstractEntity implements Entity {
    private final EntityBody body;
    private final ComponentsContainerImpl<EntityComponent> components = new ComponentsContainerImpl<>(EntityComponent.class);
    /**
     * constructor for Abstract Entity
     * @param body
     */
    public AbstractEntity(final EntityBody body /*final GameField gameField*/) {
        this.body = body;
        body.attach(this);
    }

    @Override
    public final EntityBody getBody(){
        return body;
    }

    @Override
    public void update(final double up){
        updateComponents(up);
    }

    @Override
    public void destroy(){
        components.forEach(this::remove);
        //remove(body);
    }


    @Override
    public final <C extends EntityComponent> Optional<C> get(final Class<C> component) {
        return components.get(component);
    }

    @Override
    public void remove(final EntityComponent component) {
        components.remove(component);
        component.detach();
    }

    @Override
    public void add(final EntityComponent component) {
        components.put(component);
        component.attach(this);
    }

    /**
     * Calls {@link EntityComponent#update} on the component
     * @param up
     *          Time for the update since last call
     */
    protected void updateComponents(final double up){
        components.forEach(c -> c.update(up));
    }
}
