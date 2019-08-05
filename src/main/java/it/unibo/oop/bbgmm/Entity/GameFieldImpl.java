package it.unibo.oop.bbgmm.Entity;

import it.unibo.oop.bbgmm.Control.Level;
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
    private final Set<Entity> entitiesToBeRemoved;
    private Entity player;
    private Level level;

    /**
     * {@link GameFieldImpl} constructor.
     * @param collisionSupervisor
     *      {@link CollisionSupervisor} instance
     */
    public GameFieldImpl(final CollisionSupervisor collisionSupervisor) {
        this.entities =  new LinkedHashSet<>();
        this.collisionSupervisor = collisionSupervisor;
        this.entitiesToBeRemoved  = new LinkedHashSet<>();
    }

    @Override
    public void update(final double up) {
        this.entities.stream()
                .filter(e -> !entitiesToBeRemoved.contains(e))
                .forEach(e -> e.update(up));
        this.entitiesToBeRemoved.forEach(e -> e.getDeathEvent().deregister(this::destroyEntity));
        this.entitiesToBeRemoved.forEach(this::removeEntity);
        this.entitiesToBeRemoved.clear();
        this.collisionSupervisor.searchCollision();
    }

    @Override
    public Entity addEntity(final Entity entity) {
        entities.add(entity);
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.addCollisionComponent(entity.get(Collidable.class).get());
        }
        System.out.println(entity);
        entity.getDeathEvent().register(this::destroyEntity);
        return entity;
    }

    @Override
    public Set<Entity> getEntities() {
        return Collections.unmodifiableSet(this.entities);
    }

    @Override
    public void removeEntity(final Entity entity) {
        if (entity.get(Collidable.class).isPresent()) {
            this.collisionSupervisor.removeCollisionComponent(entity.get(Collidable.class).get());
        }
        this.entities.remove(entity);
        entity.destroy();
    }

    @Override
    public Set<Entity> getWalls() {
        return this.entities.stream().filter(e -> e instanceof Wall).collect(Collectors.toSet());
    }

    @Override
    public void destroyEntity(final DeathEvent event) {
        //if an Alien is killed, it controls the number of alive Aliens, and if it is one (the last Alien killed),
        // then the next level has to start
        if (event.getEntity() instanceof Alien && this.getAliveEnemies() == 1) {
            this.entities.stream().filter(e -> !(e.getClass().equals(Player.class)))
                                  .forEach(entitiesToBeRemoved::add);
            this.level.initializeLevel();
            return;
        }
        this.entitiesToBeRemoved.add(event.getEntity());
    }



    @Override
    public void setLevel(final Level level) {
        this.level = level;
    }

    /**
     * @return the number of alive enemies
     */
    private int getAliveEnemies() {
        return (int) this.entities.stream().filter(e -> e instanceof Alien).count();
    }
}
