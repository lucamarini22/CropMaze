package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Component.Weapon;

import javafx.geometry.Point2D;
import org.junit.Test;

import java.util.List;

public class BulletTest {
    private final EntityFactory entityFactory;
    private final Player player;
    private Point2D playerPosition;

    public BulletTest() {
        entityFactory = new EntityFactoryImpl();
        playerPosition = new Point2D(20,40);
        player = entityFactory.createPlayer(playerPosition);
    }

    @Test
    public void shootRight(){
        Weapon weapon = player.get(Weapon.class).get();
        weapon.shoot(Direction.EAST);
        List<Bullet> list = weapon.getBulletList();
        System.out.println("ciao");
    }
}