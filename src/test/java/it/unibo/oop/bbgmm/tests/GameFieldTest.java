package it.unibo.oop.bbgmm.tests;

import it.unibo.oop.bbgmm.boundary.*;
import it.unibo.oop.bbgmm.control.GameController;
import it.unibo.oop.bbgmm.control.GameControllerImpl;
import it.unibo.oop.bbgmm.control.PrincipalController;
import it.unibo.oop.bbgmm.control.PrincipalControllerImpl;
import it.unibo.oop.bbgmm.entity.*;
import it.unibo.oop.bbgmm.entity.collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.entity.component.Life;
import javafx.application.Application;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

public class GameFieldTest extends Application {
    private final GameStatistics gameStatistics = new GameStatisticsImpl();
    private final AudioPlayer audioPlayer = new AudioPlayerImpl(20,20);
    private final Stage stage = new Stage();

    private final PrincipalController principalController =  new PrincipalControllerImpl(stage);
    private final GameFieldView gameFieldView = new GameFieldViewImpl(audioPlayer, new PlayerInputHandler(new Scene(new Parent() {
    })), this.principalController, stage);
    private final GameController gameController = new GameControllerImpl(gameStatistics, gameFieldView, stage, this.principalController);



    private final GameField gameField = new GameFieldImpl(new CollisionSupervisorImpl(), gameController);
    private final EntityStatistics entityStatistics = new EntityStatisticsImpl();
    private final EntityFactory entityFactory = new EntityFactoryImpl(gameField, entityStatistics,
            gameStatistics);

    @Test
    public void testEntityFactoryAndGameField() {
        //Test on EntityFactory

        //Player creation
        final Player player1 = entityFactory.createPlayer(new Point2D(2.5,2.5));
        Assert.assertEquals(player1.get(Life.class).get().getCurrentLifePoints(), entityStatistics.getPlayerHealth());
        Assert.assertNotEquals(player1.get(Life.class).get().getCurrentLifePoints(), 12);

        //Wall creation
        final Point2D positionWall1 = new Point2D(1,1);
        final Wall wall1 = entityFactory.createWall(positionWall1, new Dimension2D(5,5));
        Assert.assertEquals(wall1.getBody().getPosition(), positionWall1);

        //Enemy creation
        final int levelNumber = 5;
        this.gameStatistics.setCurrentLevel(levelNumber);
        final Alien alien1 = entityFactory.createEnemy(new Point2D(12,12));
        Assert.assertEquals(alien1.get(Life.class).get().getCurrentLifePoints(), this.entityStatistics.getEnemyHealth(this.gameStatistics.getCurrentLevel()));

        //Coin creation
        final Coin coin1 = entityFactory.createCoin(new Point2D(4.4,4.4));
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
        Assert.assertFalse(gameField.getEntities().contains(wall1));
        Assert.assertEquals(gameField.getWalls().size(), 1);
        Assert.assertEquals(gameField.getEntities().size(), 3);
    }

    @Test(expected = NullPointerException.class)
    public void testInitializeAlienBeforePlayer() {
        //Enemy creation
        entityFactory.createEnemy(new Point2D(12,12));

        //Player creation
        entityFactory.createPlayer(new Point2D(2.5,2.5));
    }

    @Override
    public void start(final Stage stage) throws Exception {
        launch();
    }
}
