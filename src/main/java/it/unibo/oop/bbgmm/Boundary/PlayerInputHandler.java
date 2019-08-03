package it.unibo.oop.bbgmm.Boundary;

import it.unibo.oop.bbgmm.Control.PlayerInputListener;
import it.unibo.oop.bbgmm.Utilities.PlayerMoves;
import javafx.event.EventType;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.*;

public class PlayerInputHandler {
    private final Set<KeyCode> input = new HashSet<>();
    private Optional<PlayerInputListener> listener = Optional.empty();

    public PlayerInputHandler(final Scene scene){
        scene.addEventHandler(KeyEvent.KEY_PRESSED,this::onPress);
        scene.addEventHandler(KeyEvent.KEY_RELEASED,this::onRelease);
    }

    private boolean checkShooting(final KeyEvent event){
        return  event.getCode().equals(KeyCode.DOWN)||
                event.getCode().equals(KeyCode.LEFT)||
                event.getCode().equals(KeyCode.RIGHT)||
                event.getCode().equals(KeyCode.UP);
    }
    private boolean checkMovement(final KeyEvent event){
        return  event.getCode().equals(KeyCode.D) ||
                event.getCode().equals(KeyCode.S) ||
                event.getCode().equals(KeyCode.W) ||
                event.getCode().equals(KeyCode.A);
    }


    /**
     * When a button is pressed
     * @param event
     *          The event triggered when a button is pressed
     */

    private void onPress(final KeyEvent event) {
        if (checkMovement(event)) {
            if (event.getCode().equals(KeyCode.D)) {
                System.out.println("D");
            }
            if (event.getCode().equals(KeyCode.A)) {
                System.out.println("A");
            }
            if (event.getCode().equals(KeyCode.S)) {
                System.out.println("S");
            }
            if (event.getCode().equals(KeyCode.W)) {
                System.out.println("W");
            }
            addKey(event);
            applyMovement();
            System.out.println("Muovi");
        }
        if (checkShooting(event)) {
            if (event.getCode().equals(KeyCode.UP)) {
                System.out.println("UP");
            }
            if (event.getCode().equals(KeyCode.DOWN)) {
                System.out.println("DOWN");
            }
            if (event.getCode().equals(KeyCode.LEFT)) {
                System.out.println("LEFT");
            }
            if (event.getCode().equals(KeyCode.RIGHT)) {
                System.out.println("RIGHT");
            }
            addKey(event);
            applyShooting();
            System.out.println("Spara");

        }
    }
    /**
     * When a button is pressed
     * @param event
     *          The event triggered when a button is pressed
     */

    private void onRelease(final KeyEvent event) {
        if(checkShooting(event) || checkMovement(event)) {
            removeKey(event);
            System.out.println("Remove key");
        }
    }


    /**
     * Add a key from the list
     * @param event
     * The key event
     */

    private void addKey(final KeyEvent event){
            this.input.add(event.getCode());
    }

    /**
     * Remove a key from the list
     * @param event
     * The key event
     */

    private void removeKey(final KeyEvent event){
        this.input.remove(event.getCode());
    }

    public void setListener(final PlayerInputListener listener){
        this.listener = Optional.of(listener);
    }

    /**
     * notify the controller that the player wants to move
     */
    private void applyMovement(){listener.ifPresent(playerInputListener ->{
        playerInputListener.move(computeMovement());
    }); }

    /**
     * notify the controller that the player wants to shot
     */
    private void applyShooting(){listener.ifPresent(playerInputListener ->{
        playerInputListener.shoot(computeShooting());
    }); }

    public Point2D computeMovement(){
        Point2D shift = Point2D.ZERO;
        if(this.input.contains(KeyCode.D)){
            shift = shift.add(PlayerMoves.RIGHT.x,PlayerMoves.RIGHT.y);
            System.out.println("ciao");
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
