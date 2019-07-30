package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.EntityView;
import it.unibo.oop.bbgmm.Boundary.ViewUtils;
import it.unibo.oop.bbgmm.Entity.Entity;

/**
 * Translates view input to model input and updates the view
 * @param <L>
 *          generic type
 */
public abstract class AbstractLifelessEntityController<L extends EntityView> extends AbstractEntityController<L> {
    /**
     * @param entity     object to control
     * @param entityView
     */
    public AbstractLifelessEntityController(final Entity entity, final L entityView) {
        super(entity, entityView);
    }

    @Override
    public void update() {
        getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
    }
}
