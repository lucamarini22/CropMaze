package it.unibo.oop.bbgmm.boundary;


import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public abstract class AbstractAliveEntityView extends AbstractEntityChangeStateView<PossibleEntityState> implements AliveEntityView {

    private PossibleEntityState currentState = PossibleEntityState.STABLE;

    public AbstractAliveEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
    }


    @Override
    public void deathView() {
        removeFromView();
    }

    @Override
    public PossibleEntityState getCurrentState() {
        return this.currentState;
    }

    @Override
    public void
    changeState(final PossibleEntityState state){
        super.changeState(state);
        currentState = state;
    }


    @Override
    public final void changeFaceDirection(final Direction direction) {
        getView().setScaleX(direction == Direction.EAST ? 1 : -1);
    }

}
