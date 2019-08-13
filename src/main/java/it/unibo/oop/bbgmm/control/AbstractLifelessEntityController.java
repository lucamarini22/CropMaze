package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.EntityView;
import it.unibo.oop.bbgmm.boundary.ViewUtils;
import it.unibo.oop.bbgmm.entity.DeathEvent;
import it.unibo.oop.bbgmm.entity.Entity;

/**
 * Translates view input to model input and updates the view.
 * @param <L>
 *          generic type.
 */
public abstract class AbstractLifelessEntityController<L extends EntityView> extends AbstractEntityController<L> {

    /**
     * Constructor.
     * @param entity
     *          related entity.
     * @param entityView
     *          related entity view.
     */
    public AbstractLifelessEntityController(final Entity entity, final L entityView) {
        super(entity, entityView);
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void update() {
        getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void entityDestruction(final DeathEvent event) {
        getEntityView().removeFromView();
    }
}
