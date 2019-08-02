package it.unibo.oop.bbgmm.Boundary;

import java.net.URL;

/**
 * This interface plays sound
 */
public interface AudioPlayer {

    void playSound(String path);


    void playMusic(String path);

    void stopMusic();

    void setSoundVolume(final double volume);

    void setMusicVolume(final double volume);
}
