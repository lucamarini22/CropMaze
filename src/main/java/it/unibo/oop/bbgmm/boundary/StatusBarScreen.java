package it.unibo.oop.bbgmm.boundary;

import javafx.scene.Node;

/**
 * Interface for the status screen.
 */
public interface StatusBarScreen extends StatusBar {
    /**
     * @return  the status main box with life and coins information.
     */
    Node getStatusBox();
}
