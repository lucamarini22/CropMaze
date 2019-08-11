package it.unibo.oop.bbgmm.utilities;

/**
 * Implementation of {@link VolumeData}.
 */
public final class VolumeDataImpl implements VolumeData {

    private Volume musicVolume = Volume.LOW;
    private Volume effectsVolume = Volume.LOW;

    @Override
    public Volume getMusicVolume() {
        return musicVolume;
    }

    @Override
    public Volume getEffectsVolume() {
        return effectsVolume;
    }

    @Override
    public void setMusicVolume(final Volume volume) {
        this.musicVolume = volume;
    }

    @Override
    public void setEffectsVolume(final Volume volume) {
        this.effectsVolume = volume;
    }
}
