package it.unibo.oop.bbgmm.Boundary;

import javafx.scene.Node;

public interface StatusBarScreen extends StatusBar{
    /**
     *
     * @return  the status main box with life and damage images
     */
    Node getStatusBox();
}
