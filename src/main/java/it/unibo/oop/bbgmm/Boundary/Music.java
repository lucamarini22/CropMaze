package it.unibo.oop.bbgmm.Boundary;

import java.net.URL;

public enum Music {

    MENU_TRACK("sounds/alienblues.wav"),

    GAME_TRACK(""),

    BUTTON_SWITCH("sounds/button_switched.mp3"),

    BUTTON_PRESS("sounds/button_clicked.mp3");

    private final URL path;

    Music(final String path){
        this.path = ClassLoader.getSystemResource(path);
    }

    public String getPath(){
        return path.toExternalForm();
    }

}
