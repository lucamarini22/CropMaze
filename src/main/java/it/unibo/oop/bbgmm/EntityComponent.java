package it.unibo.oop.bbgmm;
/**
 * Models a generic entity component.
 */
public interface EntityComponent {
    /**
     * Attaches the component to the entity
     * @param owner
     *              The entity on which to attack the component
     */
    void attach(Entity owner);

    /**
     * Detaches the component to the owner entity
     */
    void detach();

    /**
     * Sychronizes the component
     * @param delta
     *              The time passed since the last call in seconds
     */
    void update(double delta);

    /**
     * @return The entity owner of the component
     */
    Optional<? extends Entity> getOwner();
}
