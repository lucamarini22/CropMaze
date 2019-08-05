package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.awt.*;
import java.util.Set;

public class Alien extends AbstractEntity {

    private static final Dimension2D SIZE = new Dimension2D(5,5);

    private static final double WALK_SPEED= 0.5;
    private static final int DAMAGE = 5;

    /**
     *
     * @param bodyBuilder
     *              for create the entity
     * @param position
     *              its start position
     * @param health
     *              its initial life points
     */
    public Alien(final BodyBuilder bodyBuilder, final Point2D position, final int health, final Set<Entity> walls, Entity eToStalk){
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setDirection(Direction.NOTHING)
                .setMovable(true)
                .build());

        add(new LifeComponent(health));
        add(new Feet(WALK_SPEED,walls));
        add(new BrainComponent(eToStalk, get(Movement.class).get(), eToStalk.get(Life.class).get()));
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.ALIEN));
        add(new ClashComponent());
        add(new DamageComponent(DAMAGE));

    }

    @Override
    public String toString(){
        return "Alien";
    }


}
