package it.unibo.oop.bbgmm.boundary;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayerImpl implements AudioPlayer{

    private double soundVolume;
    private double musicVolume;
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
        loadMusic(path);
        mediaPlayer.play();
    }

    @Override
    public void stopMusic() {
        mediaPlayer.stop();
    }

    private void loadMusic(final String path){
        mediaPlayer = new MediaPlayer(new Media(path));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(musicVolume);
    }

    public void setSoundVolume(final double volume){
        this.soundVolume = volume;
    }

    public void setMusicVolume(final double volume){
        this.musicVolume = volume;
    }
}
