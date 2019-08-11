package it.unibo.oop.bbgmm.utilities;

/**
 * Interface of volume configuration.
 */
public interface VolumeData {

    /**
     * Return the current music volume.
     * @return music volume
     */
    Volume getMusicVolume();

    /**
     * Return the current effects volume.
     * @return effects volume
     */
    Volume getEffectsVolume();

    /**
     * Set music volume.
     * @param volume
     *      the given music volume to set
     */
    void setMusicVolume(Volume volume);

    /**
     * Set the effects sound volume.
     * @param volume
     *      the given effects volume to set
     */
    void setEffectsVolume(Volume volume);
}
