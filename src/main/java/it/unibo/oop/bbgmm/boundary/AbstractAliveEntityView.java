package it.unibo.oop.bbgmm.boundary;


import it.unibo.oop.bbgmm.entity.Direction;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public abstract class AbstractAliveEntityView extends AbstractEntityChangeStateView<PossibleEntityState> implements AliveEntityView {

    private PossibleEntityState currentState = PossibleEntityState.STABLE;
    private Direction previousDirection = Direction.EAST;
    private static final int SCALEEAST = 1;
    private static final int SCALEWEST= -1;

    public AbstractAliveEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
    }


    @Override
    public void deathView()
    {
        changeState(PossibleEntityState.DYING);
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
        if(direction == Direction.EAST || direction == Direction.WEST){
            previousDirection = direction;
        }
        if(previousDirection == Direction.EAST) {
            getView().setScaleX(SCALEEAST);
        }
        if(previousDirection == Direction.WEST) {
            getView().setScaleX(SCALEWEST);
        }

    }

}
