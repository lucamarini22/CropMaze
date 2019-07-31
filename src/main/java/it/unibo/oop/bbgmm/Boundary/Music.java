package it.unibo.oop.bbgmm.Boundary;

import java.net.URL;

public enum Music {

    MENU_TRACK("sounds/menuSong.wav"),

    GAME_TRACK(""),

<<<<<<< HEAD
    GAMEOVER_TRACK("sounds/gameOver.wav"),

    BUTTON_SWITCH("sounds/button_switched.mp3"),
=======
    BUTTON_SWITCH("sounds/button_switched.wav"),
>>>>>>> b45aaccc07f029177c838c1c2c575df0ebc615e7

    BUTTON_PRESS("sounds/button_clicked.wav");

    private final URL path;

    Music(final String path){
        this.path = ClassLoader.getSystemResource(path);
    }

    public String getPath(){
        return path.toExternalForm();
    }

}
