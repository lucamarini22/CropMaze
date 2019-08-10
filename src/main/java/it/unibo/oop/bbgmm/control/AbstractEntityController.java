package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.EntityView;
import it.unibo.oop.bbgmm.entity.DeathEvent;
import it.unibo.oop.bbgmm.entity.Entity;

/**
 * Base class for EntityController
 * @param <V>
 *     Generic EntityView type
 */
public abstract class AbstractEntityController<V extends EntityView> implements EntityController{

    private final Entity entity;
    private final V entityView;

    /**
     *
     * @param entity
     *          object to control
     * @param entityView
     *          object to update
     */
    public AbstractEntityController(final Entity entity, final V entityView) {
        this.entity = entity;
        this.entityView = entityView;
        entityView.setDimension(entity.getBody().getDimension());
        this.getEntity().getDeathEvent().register(this::entityDestruction);
    }

    @Override
    public abstract void update();

    /**
     *
     * @return the entity
     */
    protected final Entity getEntity(){ return entity; }

    /**
     *
     * @return the entity view
     */
    protected final V getEntityView(){ return entityView; }

    /**
     * it manages the entity destruction
     */
    public abstract void entityDestruction(DeathEvent event);



}
