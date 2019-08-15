package it.unibo.oop.bbgmm.control;

import it.unibo.oop.bbgmm.boundary.GameFieldView;
import it.unibo.oop.bbgmm.boundary.PlayerView;
import it.unibo.oop.bbgmm.entity.Bullet;
import it.unibo.oop.bbgmm.entity.component.Bag;
import it.unibo.oop.bbgmm.entity.component.Life;
import it.unibo.oop.bbgmm.entity.component.Weapon;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.entity.Entity;
import it.unibo.oop.bbgmm.entity.Movement;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Models the player controller.
 */
public final class PlayerController extends AliveEntityController implements PlayerInputListener {

    private final PlayerView playerView;
    private final GameFieldView gameFieldView;
    private final List<BulletController> bulletControllers;
    private final List<BulletController> removedControllers;

    /**
     * @param player
     *      The player entity to control.
     * @param playerView
     *      The player view to update.
     * @param gameFieldView
     *      The game field reference.
     */
    public PlayerController(final Entity player, final PlayerView playerView, final GameFieldView gameFieldView) {
        super(player, playerView);
        this.playerView = playerView;
        this.gameFieldView = gameFieldView;
        this.bulletControllers = new ArrayList<>();
        this.removedControllers = new ArrayList<>();
    }

    @Override
    public void move(final Point2D vector) {
        final Direction after = getEntity().getBody().getDirection();
        getEntity().get(Movement.class).ifPresent(movement -> movement.move(vector));
        final Direction before = getEntity().getBody().getDirection();
        if (!after.equals(before)) {
            movementChanged(getEntity().get(Movement.class).get());
            if (before == Direction.NOTHING) {
                faceDirectionChanged(after);
            } else {
                faceDirectionChanged(before);
            }
            //getEntityView().changeFaceDirection(getEntity().getBody().getDirection());
        }

    }

    private void updateLifeView() {
        getEntity().get(Life.class).ifPresent(life -> {
            playerView.setCurrentLifePoints(life.getCurrentLifePoints());
                });
    }



    @Override
    public void shoot(final Direction direction) {
        if (getEntity().get(Weapon.class).isPresent()) {
            final Optional<Bullet> bullet = getEntity().get(Weapon.class)
                                                 .get()
                                                 .shoot(direction);
            bullet.ifPresent(b -> createBulletController(b, direction));
        }
    }

    private void createBulletController(final Bullet bullet, final Direction direction) {
        final BulletController controller = new BulletController(bullet, gameFieldView.getEntityViewFactory().createBulletView(direction), this);
        bulletControllers.add(controller);
    }

    private void updateCoinsView() {
        getEntity().get(Bag.class).ifPresent(bag ->
                playerView.setCoins(bag.getMoney()));
    }

    @Override
    public void update() {
        super.update();
        bulletControllers.stream()
                .filter(c -> !removedControllers.contains(c))
                .forEach(EntityController::update);
        removedControllers.forEach(c -> bulletControllers.remove(c));
        removedControllers.clear();
        updateCoinsView();
        updateLifeView();
    }
    public void removeBulletController(final BulletController bulletController) {
        this.removedControllers.add(bulletController);
    }
}
