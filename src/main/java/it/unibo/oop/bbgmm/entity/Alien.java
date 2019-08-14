package it.unibo.oop.bbgmm.entity;

import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.Set;

/**
 * Class that crate an Alien ad add components.
 */
public class Alien extends AbstractEntity {

    private static final Dimension2D SIZE = new Dimension2D(2.8, 3.5);

    private static final double WALK_SPEED = 1;
    private static final int DAMAGE = 5;

    /**
     *
     * @param bodyBuilder
     *              for create the entity.
     * @param position
     *              its start position.
     * @param health
     *              its initial life points.
     * @param  walls
     *              Set of walls.
     * @param eToStalk
     *              Entity that alien will follow.
     */
    public Alien(final BodyBuilder bodyBuilder, final Point2D position, final int health, final Set<Entity> walls, final Entity eToStalk) {
        super(bodyBuilder
                .bodyPosition(position)
                .bodyDimension(SIZE)
                .bodyDirection(Direction.NOTHING)
                .build());

        add(new LifeComponent(health));
        add(new Feet(WALK_SPEED, walls));
        add(new BrainComponent(eToStalk, get(Movement.class).get(), eToStalk.get(Life.class).get()));
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.ALIEN));
        add(new ClashComponent());
        add(new DamageComponent(DAMAGE));
    }

    @Override
    public String toString() {
        return "Alien";
    }


}
