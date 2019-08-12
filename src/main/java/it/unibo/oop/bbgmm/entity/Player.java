package it.unibo.oop.bbgmm.entity;


import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.component.BagImpl;
import it.unibo.oop.bbgmm.entity.component.BodyBuilder;
import it.unibo.oop.bbgmm.entity.component.CollisionComponent;
import it.unibo.oop.bbgmm.entity.component.Feet;
import it.unibo.oop.bbgmm.entity.component.Inventory;
import it.unibo.oop.bbgmm.entity.component.LifeComponent;
import it.unibo.oop.bbgmm.entity.component.WeaponImpl;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Player extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(2.8, 3.5);
    private static final double WALKING_SPEED = 0.6;
    private static final Inventory WEAPON = Inventory.GUN;
    public Player(final BodyBuilder bodyBuilder, final Point2D position, final int health, final GameField gameField){
        super(bodyBuilder
                .bodyPosition(position)
                .bodyDimension(SIZE)
                .bodyDirection(Direction.NOTHING)
                .bodyMovable(true)
                .build());
        add(new LifeComponent(health));
        add(new Feet(WALKING_SPEED,gameField.getWalls()));
        add(new WeaponImpl(WEAPON,gameField));
        add(new CollisionComponent(this.getBody().getShape(), CollisionLabel.PLAYER));
        add(new BagImpl());
    }
}

