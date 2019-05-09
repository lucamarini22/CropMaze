package it.unibo.oop.bbgmm.Entity;

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
     *      Interface of the desider component that we want get
     * @param <C>
     *     Type of the components
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

    void destroy();
}
