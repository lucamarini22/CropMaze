package it.unibo.oop.bbgmm.tests;

import it.unibo.oop.bbgmm.entity.*;
import it.unibo.oop.bbgmm.entity.collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.entity.component.BodyBuilder;
import it.unibo.oop.bbgmm.entity.component.Brain;
import it.unibo.oop.bbgmm.entity.component.ClashComponent;
import it.unibo.oop.bbgmm.entity.component.LifeComponent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class AlienTest {

    private static final double INITIAL_POSITION_X = 20;
    private static final double INITIAL_POSITION_Y = 20;
    private static final Point2D INITAL_ALIEN_POSITION = new Point2D(INITIAL_POSITION_X,INITIAL_POSITION_Y);
    private static final int INITIAL_LIFE = 40;
    private static final Dimension2D DIMENSION = new Dimension2D(1,1);
    private static Set<Entity> walls = new HashSet<>();
    private static final BodyBuilder body = new BodyBuilder();
    private Alien alien;
    private Player player;
    private Movement feet;


    private void initializeAlien(final Set<Entity> walls){
        final GameField gameField = new GameFieldImpl(new CollisionSupervisorImpl(), null);
        player = new Player(body, new Point2D(25,23), INITIAL_LIFE, gameField);
        alien = new Alien(body, INITAL_ALIEN_POSITION, INITIAL_LIFE, walls, player);

    }

    @Test
    public void testInitPosition(){
        initializeAlien(null);
        Assert.assertEquals(this.alien.getBody().getPosition(), new Point2D(INITIAL_POSITION_X, INITIAL_POSITION_Y));
    }

    @Test
    public void testAddExistingComponent(){
        initializeAlien(walls);
        try {
            alien.add(new ClashComponent());
            alien.add(new LifeComponent(30));
        }
        catch(IllegalArgumentException e){
            
        }

        feet = alien.get(Movement.class).get();
        //remove and add the same component, it doesn't make problem
        try{
            alien.remove(feet);
            alien.add(feet);
        }
        catch(IllegalArgumentException e){
        }
    }

    @Test
    public void testMoveToPlayerWithoutWalls(){
        initializeAlien(walls);

        for(int i = 0 ; i < 8 ; i++ ) {
            alien.get(Brain.class).get().followPlayer();
        }

        Assert.assertEquals(alien.getBody().getPosition(),player.getBody().getPosition());
        /*it moves up (north) for 3 time and right (east) for 5, when it meet the player
        * it will be positioned in east direction */
        Assert.assertEquals(alien.getBody().getDirection(), Direction.EAST);
    }

    @Test
    public void testMoveToPlayerWithWalls(){
        final Wall wall = new Wall(body,new Point2D(23,23), DIMENSION);
        walls.add(wall);
        initializeAlien(walls);
        for(int i = 0 ; i < 8 ; i++ ) {
            alien.get(Brain.class).get().followPlayer();
        }

        //alien can't find the player because there is a wall in the middle and it change its State to Stable
        Assert.assertFalse("alien find a wall", alien.getBody().getPosition().equals(player.getBody().getPosition()));
        Assert.assertEquals(alien.get(Movement.class).get().getState(), Movement.State.STABLE);

        //now if I move the player 5 position down and 2 left, the alien can find it
        player.getBody().addPosition(new Point2D(-2,- 5));
        for(int i = 0 ; i < 8 ; i++ ) {
            alien.get(Brain.class).get().followPlayer();
        }
        Assert.assertEquals(alien.getBody().getPosition(), player.getBody().getPosition());
        Assert.assertEquals(alien.get(Movement.class).get().getState(), Movement.State.WALKING);



    }


}
