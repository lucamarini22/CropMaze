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

public class PlayerController extends AliveEntityController implements PlayerInputListener {

    private final PlayerView playerView;
    private final GameFieldView gameFieldView;
    private final List<BulletController> bulletControllers;
    private final List<BulletController> removedControllers;

    public PlayerController(final Entity player, final PlayerView playerView, final GameFieldView gameFieldView){
        super(player, playerView);
        this.playerView = playerView;
        this.gameFieldView = gameFieldView;
        this.bulletControllers = new ArrayList<>();
        this.removedControllers = new ArrayList<>();
    }

    @Override
    public void lifeChange(final Life life) {
        super.lifeChange(life);
    }

    @Override
    public void move(final Point2D vector) {
        Direction after = getEntity().getBody().getDirection();
        getEntity().get(Movement.class).ifPresent(movement -> movement.move(vector));
        Direction before = getEntity().getBody().getDirection();
        System.out.println("Player Position: "+ getEntity().getBody().getPosition());
        System.out.println(" move in player controller " + getEntity().get(Movement.class).get().getState());
        if(after != before) {
            movementChanged(getEntity().get(Movement.class).get());
            getEntityView().changeFaceDirection(getEntity().getBody().getDirection());
        }

    }

    private void updateLifeView(){
        getEntity().get(Life.class).ifPresent(life ->{
            playerView.setCurrentLifePoints(life.getCurrentLifePoints());
                });
        System.out.println("CURRENT LIFE"+getEntity().get(Life.class).get().getCurrentLifePoints());
    }



    @Override
    public void shoot(Direction direction) {
        if(getEntity().get(Weapon.class).isPresent()){
            Optional<Bullet> bullet = getEntity().get(Weapon.class)
                                                 .get()
                                                 .shoot(direction);
            bullet.ifPresent(b -> createBulletController(b, direction));
        }
    }

    private void createBulletController(Bullet bullet, Direction direction){
        BulletController controller = new BulletController(bullet, gameFieldView.getEntityViewFactory().createBulletView(direction), this);
        bulletControllers.add(controller);
    }

    private void updateCoinsView(){
        getEntity().get(Bag.class).ifPresent(bag ->
                playerView.setCoins(bag.getMoney()));
    }

    @Override
    public void update(){
        super.update();
        bulletControllers.stream()
                .filter(c -> !removedControllers.contains(c))
                .forEach(EntityController::update);
        removedControllers.forEach(c -> bulletControllers.remove(c));
        removedControllers.clear();
        updateCoinsView();
        updateLifeView();
    }

    public void removeBulletController(final BulletController bulletController){
        this.removedControllers.add(bulletController);
    }
}
