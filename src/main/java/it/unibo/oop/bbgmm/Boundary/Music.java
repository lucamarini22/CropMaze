package it.unibo.oop.bbgmm.Boundary;

import java.net.URL;

public enum Music {

    TRACK1(""),

    TRACK2(""),

    BUTTONSWITCH("sounds/button_switched.mp3"),

    BUTTONPRESS("sounds/button_clicked.mp3");

    private final URL path;

    Music(final String path){
        this.path = ClassLoader.getSystemResource(path);
    }

    public String getPath(){
        return path.toExternalForm();
    }

}
