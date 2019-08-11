package it.unibo.oop.bbgmm.boundary;

/**
 * This interface plays sound.
 */
public interface AudioPlayer {

    /**
     * Plays the given sound effect.
     * @param path
     *      the effect sound path
     */
    void playSound(String path);

    /**
     * Plays the given music.
     * @param path
     *      the music path
     */
    void playMusic(String path);

    /**
     * Stop the current music.
     */
    void stopMusic();

    /**
     * Method to set the effects volume.
     * @param volume
     *      the new effects volume
     */
    void setSoundVolume(double volume);

    /**
     * Method to set the music volume.
     * @param volume
     *      the new music volume
     */
    void setMusicVolume(double volume);
}
