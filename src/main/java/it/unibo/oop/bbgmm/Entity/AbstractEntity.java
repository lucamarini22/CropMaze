package it.unibo.oop.bbgmm.Entity;

/**
 * base class for entity type
 */

public abstract class AbstractEntity extends Entity {
    private final EntityBody body;

    /**
     * constructor for Abstract Entity
     * @param body
     */
    public AbstractEntity(EntityBody body) {
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
        remove(body);
    }


    @Override
    public <C extends EntityComponent> Optional<C> get(Class<C> component) {
        return component.attach(this);
    }

    @Override
    public void remove(EntityComponent component) {
        components.remove(component);
        component.detach();
    }

    @Override
    public void add(EntityComponent component) {
        components.put(component);
        component.attach(this);
    }
}
