package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PlayerInputListener;
import it.unibo.oop.bbgmm.Utilities.PlayerMoves;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;

public class PlayerInputHandler {
    private final Set<KeyCode> input = new HashSet<>();
    private Optional<PlayerInputListener> listener = Optional.empty();

    public PlayerInputHandler(final Scene scene){
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::onKeyPressed);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, this::onKeyPressed);
    }

    /**
     * When a button is pressed
     * @param event
     *          The event triggered when a button is pressed
     */

    public void onKeyPressed(final KeyEvent event){
        if(event.getCode().equals(KeyCode.D) ||
                event.getCode().equals(KeyCode.S) ||
                event.getCode().equals(KeyCode.W) ||
                event.getCode().equals(KeyCode.A)||
                event.getCode().equals(KeyCode.DOWN)||
                event.getCode().equals(KeyCode.LEFT)||
                event.getCode().equals(KeyCode.RIGHT)||
                event.getCode().equals(KeyCode.UP)) {

            if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
                this.input.add(event.getCode());
            }
            if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
                this.input.remove(event.getCode());
            }
        }
    }

    public void setListener(final Optional<PlayerInputListener> listener){
        this.listener = listener;
    }
    /**
     * notify the controller that the player wants to move
     */
    public void applyMovement(){listener.ifPresent(playerInputListener -> playerInputListener.move(computeMovement()));}

    public Point2D computeMovement(){
        Point2D shift = Point2D.ZERO;
        if(this.input.contains(KeyCode.D)){
            shift = shift.add(PlayerMoves.RIGHT.x,PlayerMoves.RIGHT.y);
        }
        if(this.input.contains(KeyCode.S)){
            shift = shift.add(PlayerMoves.DOWN.x,PlayerMoves.DOWN.y);
        }
        if(this.input.contains(KeyCode.W)){
            shift = shift.add(PlayerMoves.UP.x,PlayerMoves.UP.y);
        }
        if(this.input.contains(KeyCode.A)){
            shift = shift.add(PlayerMoves.LEFT.x,PlayerMoves.LEFT.y);
        }

        return shift;
    }

    public Point2D computeShooting(){
        Point2D shift = Point2D.ZERO;
        if(this.input.contains(KeyCode.UP)){
            shift = shift.add(PlayerMoves.UP.x,PlayerMoves.UP.y);
        }
        if(this.input.contains(KeyCode.DOWN)){
            shift = shift.add(PlayerMoves.DOWN.x,PlayerMoves.DOWN.y);
        }
        if(this.input.contains(KeyCode.LEFT)){
            shift = shift.add(PlayerMoves.LEFT.x,PlayerMoves.LEFT.y);
        }
        if(this.input.contains(KeyCode.RIGHT)){
            shift = shift.add(PlayerMoves.RIGHT.x,PlayerMoves.RIGHT.y);
        }
        return shift;
    }
}
