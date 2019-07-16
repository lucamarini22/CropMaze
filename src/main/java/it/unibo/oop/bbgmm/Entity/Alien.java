package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.awt.*;

public class Alien extends AbstractEntity {

    private static final Dimension2D SIZE = new Dimension2D(1.1,1.2);

    private static final double WALK_SPEED=1;
    private static final Inventory weapon = Inventory.GUN;

    /**
     *
     * @param bodyBuilder
     *              for create the entity
     * @param position
     *              its start position
     * @param health
     *              its initial life points
     */
    public Alien(final BodyBuilder bodyBuilder, final Point2D position, final int health){
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setDirection(Direction.NOTHING)
                .setMovable(true)
                .build());

        add(new LifeComponent(health));
        add(new WeaponImpl(weapon));
        add(new Feet(WALK_SPEED));
        add(new CollisionComponent(this,new Rectangle(), CollisionLabel.ALIEN));

    }

    @Override
    public String toString(){
        return "Alien";
    }


}
