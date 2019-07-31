package it.unibo.oop.bbgmm.Boundary;

import java.net.URL;

public enum Music {

    MENU_TRACK("sounds/menuSong.wav"),

    GAME_TRACK(""),

    BUTTON_SWITCH("sounds/button_switched.wav"),

    BUTTON_PRESS("sounds/button_clicked.wav");

    private final URL path;

    Music(final String path){
        this.path = ClassLoader.getSystemResource(path);
    }

    public String getPath(){
        return path.toExternalForm();
    }

}
