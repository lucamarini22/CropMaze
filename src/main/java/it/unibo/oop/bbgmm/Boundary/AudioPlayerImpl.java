package it.unibo.oop.bbgmm.Boundary;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.applet.Applet;
import java.net.URL;

public class AudioPlayerImpl implements AudioPlayer{

    private final double soundVolume;
    private final double musicVolume;
    private MediaPlayer mediaPlayer;

    public AudioPlayerImpl(final double soundVolume, final double musicVolume){

        this.soundVolume = soundVolume;
        this.musicVolume = musicVolume;
        //far partire la traccia 1

    }
    @Override
    public void playSound(String path) {
        new AudioClip(path).play(soundVolume);
    }

    @Override
    public void playMusic(String path) {
        stopMusic();
        loadMusic(path);
        mediaPlayer.setVolume(musicVolume);
        mediaPlayer.play();

    }

    @Override
    public void stopMusic() {

    }

    private void loadMusic(final String path){
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(path).toExternalForm()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(musicVolume);
    }
}
