package it.unibo.oop.bbgmm.Utilities;

/**
 * Interface of volume configuration
 */
public interface VolumeData {

    Volume getMusicVolume();

    Volume getEffectsVolume();

    void setMusicVolume(Volume volume);

    void setEffectsVolume(Volume volume);
}
