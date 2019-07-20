package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Entity.Entity;
import it.unibo.oop.bbgmm.Entity.Wall;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.Set;
import java.util.stream.Collectors;

public class WallCheckerImpl implements WallChecker {
    private final Set<Entity> walls;

    public WallCheckerImpl(final Set<Entity> entities){
        this.walls = entities.stream().filter(e -> e.getClass().equals(Wall.class)).collect(Collectors.toSet());
    }

    @Override
    public boolean willCollide(Point2D position, Dimension2D dimension) {
        Rectangle2D shape = new Rectangle2D(position.getX(),position.getY(),dimension.getWidth(),dimension.getHeight());
        return this.walls.stream().anyMatch(w -> w.getBody().getShape().intersects(shape));
    }
}
