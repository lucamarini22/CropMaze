package it.unibo.oop.bbgmm.entity.component;

import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.Entity;
import it.unibo.oop.bbgmm.entity.Movement;
import javafx.geometry.Point2D;

/**
 * permit to follow a determinate entity and change the desired direction.
 */
public class BrainComponent extends AbstractEntityComponent implements Brain {

    private static final double MAX_TIME = 0.2;
    private static final double RANGE = 0.5;
    private final Entity entityToFollow;
    private final Movement feet;
    private double time = MAX_TIME;
    private final Life playerLife;


    /**
     * Constructor.
     * @param eToFollow
     *          entity to stalk.
     * @param feet
     *          component feet.
     * @param life
     *          component life.
     */
    public BrainComponent(final Entity eToFollow, final Movement feet, final Life life) {
        super();
        this.entityToFollow = eToFollow;
        this.feet = feet;
        this.playerLife = life;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void update(final double delta) {
        this.time = this.time - delta;
        super.update(delta);
        if (this.time <= 0) {
            followPlayer();
            this.time = MAX_TIME;
        }
    }
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void followPlayer() {
        final Point2D positionToFollow = this.entityToFollow.getBody().getPosition();
        final Point2D currentPosition = getOwner().get().getBody().getPosition();

        Direction newDirection;

        if (positionToFollow.getY() < currentPosition.getY()) {
            newDirection = Direction.SOUTH;
        } else {
            newDirection = Direction.NORTH;
        }

        if (positionToFollow.getY() <= currentPosition.getY() + RANGE
                && positionToFollow.getY() >= currentPosition.getY() - RANGE) {
            if (positionToFollow.getX() < currentPosition.getX()) {
                newDirection = Direction.WEST;
            } else {
                newDirection = Direction.EAST;
            }
        }


        //if the alien and the player are colliding the alien must not move
        if (getOwner().get().getBody().getShape().getBoundsInLocal().intersects(this.entityToFollow.getBody().getShape().getBoundsInLocal())
           || this.playerLife.isDead()) {
            newDirection = Direction.NOTHING;
        }

        final Point2D newPosition = this.feet.calculateVector(newDirection);
        this.feet.move(newPosition);
    }
}
