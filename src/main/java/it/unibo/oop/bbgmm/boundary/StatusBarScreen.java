package it.unibo.oop.bbgmm.boundary;

import javafx.scene.Node;

public interface StatusBarScreen extends StatusBar{
    /**
     *
     * @return  the status main box with life and coins information
     */
    Node getStatusBox();
}
