package it.unibo.oop.bbgmm.Boundary;

/**
 * This interface plays sound
 */
public interface AudioPlayer {

    void playSound(String path);

    void playMusic(String path);

    void stopMusic();
}
