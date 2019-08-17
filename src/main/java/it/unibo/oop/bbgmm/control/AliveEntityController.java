package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.AliveEntityView;
import it.unibo.oop.bbgmm.boundary.PossibleEntityState;
import it.unibo.oop.bbgmm.utilities.ViewUtils;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.component.Life;
import it.unibo.oop.bbgmm.entity.DeathEvent;
import it.unibo.oop.bbgmm.entity.Entity;
import it.unibo.oop.bbgmm.entity.Movement;

import java.util.HashMap;
import java.util.Map;

/**
 * Translates view input to model input and updates the view.
 */
public class AliveEntityController extends AbstractEntityController<AliveEntityView> {

    private final Map<Movement.State, PossibleEntityState> stateMap = creatureStateMap();

    private Map<Movement.State, PossibleEntityState> creatureStateMap() {
        final Map<Movement.State, PossibleEntityState> stateMap = new HashMap<>();
        stateMap.put(Movement.State.STABLE, PossibleEntityState.STABLE);
        stateMap.put(Movement.State.DYING, PossibleEntityState.DYING);
        stateMap.put(Movement.State.WALKING, PossibleEntityState.WALKING);

        return stateMap;
    }

    /**
     * Constructor for alive entity controller.
     * @param entity
     *          the related entity.
     * @param entityView
     *          the related entity view.
     */
    public AliveEntityController(final Entity entity, final AliveEntityView entityView) {

        super(entity, entityView);
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()) {
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        }
    }

    /**
     * it change the entity state.
     * @param movement
     *         movement that the entity do.
     */
    public void movementChanged(final Movement movement) {
        getEntityView().changeState(stateMap.getOrDefault(movement.getState(), PossibleEntityState.STABLE));
    }

    /**
     * it change the entity face direction.
     * @param direction
     *          where the entity is going.
     */
    public void faceDirectionChanged(final Direction direction) {
        getEntityView().changeFaceDirection(direction);
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void entityDestruction(final DeathEvent event) {
            getEntityView().deathView();
    }
}
