package it.unibo.oop.bbgmm.tests;
import static org.junit.Assert.assertEquals;

import it.unibo.oop.bbgmm.entity.collision.Collidable;
import it.unibo.oop.bbgmm.entity.collision.CollisionLabel;
import it.unibo.oop.bbgmm.entity.collision.CollisionSupervisor;
import it.unibo.oop.bbgmm.entity.collision.CollisionSupervisorImpl;
import it.unibo.oop.bbgmm.entity.component.CollisionComponent;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Collision Supervisor class
 */
public class CollisionTest {

    private static final double DELTA = 0.00001;
    private CollisionSupervisor collisionSupervisor;
    private boolean result;

    @Before
    public void setUp(){
        this.collisionSupervisor = new CollisionSupervisorImpl();
    }

    @Test
    public void testBetweenRectangles() {
        final Rectangle rectangleTall = new Rectangle(1,2);
        final Rectangle rectangleLarge = new Rectangle(2,1);
        final Rectangle square1 = new Rectangle(1,1);
        final Rectangle square2 = new Rectangle(1,1);
        this.checkCollision(rectangleTall, Point2D.ZERO, rectangleLarge, Point2D.ZERO, true);
        this.checkCollision(rectangleLarge, Point2D.ZERO, square1, Point2D.ZERO, true);
        this.checkCollision(rectangleTall, Point2D.ZERO, rectangleLarge, new Point2D(3,0), false);
        this.checkCollision(rectangleTall, Point2D.ZERO, square2, new Point2D(0, 1), true);
        this.checkCollision(rectangleTall, new Point2D(2,2), rectangleLarge, new Point2D(3, 2), true);
        this.checkCollision(square1, Point2D.ZERO, square2, new Point2D(1 + DELTA, 1 + DELTA), false);
        this.checkCollision(square1, Point2D.ZERO, square2, new Point2D(1 - DELTA, 1 - DELTA), true);
    }

    private void checkCollision(final Rectangle r1,final Point2D p1,final Rectangle r2,final Point2D p2,final boolean expected) {
        final Collidable c1 = new CollisionComponent(r1, CollisionLabel.COIN);
        final Collidable c2 = new CollisionComponent(r2, CollisionLabel.PLAYER);
        c1.getShape().setX(p1.getX());
        c1.getShape().setY(p1.getY());
        c2.getShape().setX(p2.getX());
        c2.getShape().setY(p2.getY());
        this.collisionSupervisor.addCollisionComponent(c1);
        this.collisionSupervisor.addCollisionComponent(c2);
        this.result = false;

        c1.getEvent().register(c -> {
            this.result = true;
            assertEquals(c.getCollisionComponent(), c2);
        });

        this.collisionSupervisor.searchCollision();
        this.collisionSupervisor.removeCollisionComponent(c1);
        this.collisionSupervisor.removeCollisionComponent(c2);
        assertEquals(expected, result);
    }

}
