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
    public AbstractLifelessEntityController(Entity entity, L entityView) {
        super(entity, entityView);
    }

    @Override
    public final void update(){
        //creata classe ViewUtils, ma si potrebbe non usare più avanti
        getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
    }
}
