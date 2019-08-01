package it.unibo.oop.bbgmm.Boundary;


import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public abstract class AbstractAliveEntityView extends AbstractEntityChangeStateView<PossibleEntityState> implements AliveEntityView {

    public AbstractAliveEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
    }

    @Override
    public void deathView() {
        removeFromView();
    }
}
