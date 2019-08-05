package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.*;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.Entity.Component.Life;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

public class GameFieldTest {
    private final GameField gameField = new GameFieldImpl(new CollisionSupervisorImpl());
    private final EntityStatistics entityStatistics = new EntityStatisticsImpl();
    private final GameStatistics gameStatistics = new GameStatisticsImpl();
    private final EntityFactory entityFactory = new EntityFactoryImpl(gameField, entityStatistics,
            gameStatistics);

    @Test
    public void testEntityFactoryAndGameField() {
        //Test on EntityFactory

        //Wall creation
        Point2D positionWall1 = new Point2D(1,1);
        Wall wall1 = entityFactory.createWall(positionWall1, new Dimension2D(5,5));
        Assert.assertEquals(wall1.getBody().getPosition(), positionWall1);

        //Enemy creation
        int levelNumber = 5;
        this.gameStatistics.setCurrentLevel(levelNumber);
        Alien alien1 = entityFactory.createEnemy(new Point2D(12,12));
        Assert.assertEquals(alien1.get(Life.class).get().getCurrentLifePoints(), this.entityStatistics.getEnemyHealth(this.gameStatistics.getCurrentLevel()));

        //Player creation
        Player player1 = entityFactory.createPlayer(new Point2D(2.5,2.5));
        Assert.assertEquals(player1.get(Life.class).get().getCurrentLifePoints(), entityStatistics.getPlayerHealth());
        Assert.assertNotEquals(player1.get(Life.class).get().getCurrentLifePoints(), 12);

        //Coin creation
        Coin coin1 = entityFactory.createCoin(new Point2D(4.4,4.4));
        Assert.assertEquals(coin1.getClass(), Coin.class);


        //Test on GameField

        Assert.assertTrue(this.gameField.getEntities().isEmpty());
        this.gameField.addEntity(wall1);
        Assert.assertEquals(this.gameField.getEntities().size(), 1);
        this.gameField.addEntity(player1);
        this.gameField.addEntity(entityFactory.createWall(new Point2D(2,3), new Dimension2D(6,8)));
        this.gameField.addEntity(coin1);
        Assert.assertEquals(gameField.getWalls().size(), 2);

        this.gameField.removeEntity(wall1);
        Assert.assertEquals(gameField.getWalls().size(), 1);
        Assert.assertEquals(gameField.getEntities().size(), 3);
    }
}
