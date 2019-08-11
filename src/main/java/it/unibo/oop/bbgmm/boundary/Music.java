package it.unibo.oop.bbgmm.boundary;

import java.net.URL;

/**
 * Game's music and sounds.
 */
public enum Music {

    /**
     * MENU_TRACK.
     */
    MENU_TRACK("sounds/menuSong.wav"),

    /**
     *
     */
    GAME_TRACK("sounds/gameSong.wav"),

    /**
     * GAMEOVER_TRACK.
     */
    GAMEOVER_TRACK("sounds/gameOver.wav"),

    /**
     * BUTTON_SWITCH.
     */
    BUTTON_SWITCH("sounds/button_switched.wav"),

    /**
     * BUTTON_PRESS.
     */
    BUTTON_PRESS("sounds/button_clicked.wav"),

    /**
     * BULLET_SHOT.
     */
    BULLET_SHOT("sounds/shotSound.wav");

    private final URL path;

    Music(final String path) {
        this.path = ClassLoader.getSystemResource(path);
    }

    /**
     * Return the music path.
     * @return
     *      the music path
     */
    public String getPath() {
        return path.toExternalForm();
    }

}
