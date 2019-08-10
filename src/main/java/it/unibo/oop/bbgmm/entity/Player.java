package it.unibo.oop.bbgmm.entity;


import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.component.*;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Player extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(2.8, 3.5);
    private static final double WALKING_SPEED = 0.6;
    private static final Inventory weapon = Inventory.GUN;
    public Player(final BodyBuilder bodyBuilder, final Point2D position, final int health, final GameField gameField){
        super(bodyBuilder
                .setPosition(position)
                .setDimension(SIZE)
                .setDirection(Direction.NOTHING)
                .setMovable(true)
                .build());
        add(new LifeComponent(health));
        add(new Feet(WALKING_SPEED,gameField.getWalls()));
        add(new WeaponImpl(weapon,gameField));
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.PLAYER));
        add(new BagImpl());
    }
}

