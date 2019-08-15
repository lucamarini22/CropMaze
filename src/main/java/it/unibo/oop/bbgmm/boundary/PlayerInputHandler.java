package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.control.PlayerInputListener;
import it.unibo.oop.bbgmm.entity.Direction;
import it.unibo.oop.bbgmm.utilities.PlayerMoves;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Manage input from keyboard.
 */
public final class PlayerInputHandler {
    private final Set<KeyCode> input = new HashSet<>();
    private Optional<PlayerInputListener> listener = Optional.empty();
    private final Scene scene;

    /**
     * @param scene
     *      The scene in which to intercept the key events.
     */
    public PlayerInputHandler(final Scene scene) {
        this.scene = scene;
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::onPress);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::onRelease);
    }

    private boolean checkShooting(final KeyEvent event) {
        return  event.getCode().equals(KeyCode.DOWN)
                || event.getCode().equals(KeyCode.LEFT)
                || event.getCode().equals(KeyCode.RIGHT)
                || event.getCode().equals(KeyCode.UP);
    }
    private boolean checkMovement(final KeyEvent event) {
        return  event.getCode().equals(KeyCode.D)
                || event.getCode().equals(KeyCode.S)
                || event.getCode().equals(KeyCode.W)
                || event.getCode().equals(KeyCode.A);
    }


    /**
     * When a button is pressed.
     * @param event
     *          The event triggered when a button is pressed.
     */

    private void onPress(final KeyEvent event) {
        if (checkMovement(event)) {
            addKey(event);
            applyMovement();
        }
        if (checkShooting(event)) {
            addKey(event);
            applyShooting();

        }
    }
    /**
     * When a button is pressed.
     * @param event
     *          The event triggered when a button is pressed.
     */

    private void onRelease(final KeyEvent event) {
        if (checkShooting(event) || checkMovement(event)) {
            removeKey(event);
        }
        if (!input.stream().anyMatch(keyCode -> keyCode.equals(KeyCode.UP)
                || keyCode.equals(KeyCode.DOWN)
                || keyCode.equals(KeyCode.RIGHT)
                || keyCode.equals(KeyCode.LEFT))) {
            applyMovement();
        }
    }


    /**
     * Add a key from the list.
     * @param event
     *      The key event.
     */
    private void addKey(final KeyEvent event) {
            this.input.add(event.getCode());
    }

    /**
     * Remove a key from the list.
     * @param event
     *      The key event.
     */
    private void removeKey(final KeyEvent event) {
        this.input.remove(event.getCode());
    }

    /**
     * Set the player input listener.
     * @param listener
     *      The player input listener.
     */
    public void setListener(final PlayerInputListener listener) {
        this.listener = Optional.of(listener);
    }

    /**
     * notify the controller that the player wants to move.
     */
    private void applyMovement() {
        listener.ifPresent(playerInputListener -> {
        playerInputListener.move(computeMovement());
    }); }

    /**
     * notify the controller that the player wants to shot.
     */
    private void applyShooting() {
        listener.ifPresent(playerInputListener -> {
        playerInputListener.shoot(computeShooting());
    }); }

    /**
     * Create the distance vector based on the key code registered.
     * @return The distance vector.
     */
    public Point2D computeMovement() {
        Point2D shift = Point2D.ZERO;
        if (this.input.contains(KeyCode.D)) {
            shift = shift.add(PlayerMoves.RIGHT.getX(), PlayerMoves.RIGHT.getY());
        }
        if (this.input.contains(KeyCode.S)) {
            shift = shift.add(PlayerMoves.DOWN.getX(), PlayerMoves.DOWN.getY());
        }
        if (this.input.contains(KeyCode.W)) {
            shift = shift.add(PlayerMoves.UP.getX(), PlayerMoves.UP.getY());
        }
        if (this.input.contains(KeyCode.A)) {
            shift = shift.add(PlayerMoves.LEFT.getX(), PlayerMoves.LEFT.getY());
        }

        return shift;
    }

    /**
     * Calculate the bullet direction based on the key code.
     * @return The bullet direction.
     */
    public Direction computeShooting() {

        Direction direction = Direction.NOTHING;

        if (this.input.contains(KeyCode.UP)) {
            direction = Direction.NORTH;
        }
        if (this.input.contains(KeyCode.DOWN)) {
            direction = Direction.SOUTH;
        }
        if (this.input.contains(KeyCode.LEFT)) {
            direction = Direction.WEST;
        }
        if (this.input.contains(KeyCode.RIGHT)) {
            direction = Direction.EAST;
        }
        return direction;
    }

    /**
     * Method called to remove the EventHandler.
     */
    public void reset() {
        scene.removeEventHandler(KeyEvent.KEY_PRESSED, this::onPress);
        scene.removeEventHandler(KeyEvent.KEY_RELEASED, this::onRelease);
        this.listener = Optional.empty();
    }

    /**
     * Method called to clear the input set.
     */
    public void clearInput() {
        this.input.clear();
    }
}
