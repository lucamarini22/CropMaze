package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Boundary.AliveEntityView;
import it.unibo.oop.bbgmm.Boundary.PossibleEntityState;
import it.unibo.oop.bbgmm.Boundary.ViewUtils;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import it.unibo.oop.bbgmm.Entity.DeathEvent;
import it.unibo.oop.bbgmm.Entity.Direction;
import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Movement;

import java.util.HashMap;
import java.util.Map;

public class AliveEntityController extends AbstractEntityController<AliveEntityView> {

    private final Map<Movement.State, PossibleEntityState> stateMap = creatureStateMap();

    private Map<Movement.State, PossibleEntityState> creatureStateMap() {
        final Map<Movement.State, PossibleEntityState> stateMap = new HashMap<>();
        stateMap.put(Movement.State.STABLE, PossibleEntityState.STABLE);
        stateMap.put(Movement.State.DYING, PossibleEntityState.DYING);
        stateMap.put(Movement.State.WALKING, PossibleEntityState.WALKING);

        return stateMap;
    }

    public AliveEntityController(final Entity entity, final AliveEntityView entityView) {

        super(entity, entityView);
    }

    @Override
    public void update(){
        if(getEntity().get(Life.class).isPresent() && getEntity().get(Life.class).get().isAlive()){
            getEntityView().setPosition(ViewUtils.worldPointToFX(getEntity().getBody().getPosition()));
        } else {
            getEntityView().deathView();
        }
    }

    /**
     * it change the entity state
     * @param movement
     *         movement that the entity do
     */
    public void movementChanged(final Movement movement){
        getEntityView().changeState(stateMap.getOrDefault(movement.getState(), PossibleEntityState.STABLE ));
    }

    /**
     * it change the entity face direction
     * @param direction
     *          where the entity is going
     */
    /*public void faceDirectionChanged(final Direction direction){
        getEntityView().changeFaceDirection(direction);
    }*/

    /**
     * it updates the entity state
     * @param life
     *          the life of the entity
     */
    public void lifeChange(final Life life){
        if(life.isDead()){
            getEntityView().changeState(PossibleEntityState.DYING);
        }
        //else if (life.getCurrentLifePoints() > 0){
            //the entity is damaged but still alive
            //getEntityView().changeState(PossibleEntityState.SUFFERING);
        //}
    }

    @Override
    public void entityDestruction(DeathEvent event) {
        if(!(this.getEntity().get(Life.class).isPresent() && this.getEntity().get(Life.class).get().isDead())){
            getEntityView().removeFromView();
        }
    }
}
