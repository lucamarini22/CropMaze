package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Entity.Collision.Collidable;
import it.unibo.oop.bbgmm.Entity.Collision.CollisionSupervisor;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Runs the game field.
 */
public final class GameFieldImpl implements GameField {
    private final Set<Entity> entities;
    private final CollisionSupervisor collisionSupervisor;

    /**
     * {@link GameFieldImpl} constructor.
     * @param collisionSupervisor
     *      {@link CollisionSupervisor} instance
     */
    public GameFieldImpl(final CollisionSupervisor collisionSupervisor) {
        this.entities =  new LinkedHashSet<>();
        this.collisionSupervisor = collisionSupervisor;
    }

    @Override
    public void update(final double up) {
        this.entities.forEach(e -> e.update(up));
        this.collisionSupervisor.searchCollision();
    }

    @Override
    public Entity addEntity(final Entity entity) {
        entities.add(entity);
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.addCollisionComponent(entity.get(Collidable.class).get());
        }
        return entity;
    }

    @Override
    public Set<Entity> getEntities() {
        return Collections.unmodifiableSet(this.entities);
    }

    @Override
    public void removeEntity(final Entity entity) {
        this.entities.remove(entity);
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.removeCollisionComponent(entity.get(Collidable.class).get());
        }
    }

    @Override
    public Set<Entity> getWalls() {
        return this.entities.stream().filter(e -> e.getClass().equals(Wall.class)).collect(Collectors.toSet());
    }
}
