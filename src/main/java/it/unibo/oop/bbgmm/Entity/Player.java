package it.unibo.oop.bbgmm.Entity;


import it.unibo.oop.bbgmm.Entity.Collision.CollisionLabel;
import it.unibo.oop.bbgmm.Entity.Component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.awt.*;

public class Player extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1.1,1.2);
    private static final double WALKING_SPEED = 1;
    private static final Inventory weapon = Inventory.GUN;
    public Player(final BodyBuilder bodyBuilder, final Point2D position, final int health){
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setDirection(Direction.NOTHING)
                .setMovable(true)
                .build());
        add(new LifeComponent(health));
        add(new Feet(WALKING_SPEED));
        add(new WeaponImpl(weapon));
        add(new CollisionComponent(this,new Rectangle(), CollisionLabel.PLAYER));
    }

}

