package it.unibo.oop.bbgmm.boundary;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Implementation of {@link AudioPlayer}.
 */
public final class AudioPlayerImpl implements AudioPlayer {

    private double soundVolume;
    private double musicVolume;
    private MediaPlayer mediaPlayer;

    /**
     * Creates a new AudioPlayer instance.
     * @param soundVolume
     *      the given sound volume
     * @param musicVolume
     *      the given music volume
     */
    public AudioPlayerImpl(final double soundVolume, final double musicVolume) {

        this.soundVolume = soundVolume;
        this.musicVolume = musicVolume;
    }
    @Override
    public void playSound(final String path) {
        new AudioClip(path).play(soundVolume);
    }

    @Override
    public void playMusic(final String path) {
        loadMusic(path);
        mediaPlayer.play();
    }

    @Override
    public void stopMusic() {
        mediaPlayer.stop();
    }

    private void loadMusic(final String path) {
        mediaPlayer = new MediaPlayer(new Media(path));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(musicVolume);
    }

    @Override
    public void setSoundVolume(final double volume) {
        this.soundVolume = volume;
    }

    @Override
    public void setMusicVolume(final double volume) {
        this.musicVolume = volume;
    }
}
