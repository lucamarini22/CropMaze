package it.unibo.oop.bbgmm.Utilities;

public class VolumeDataImpl implements VolumeData {

    private  Volume musicVolume = Volume.LOW;
    private  Volume effectsVolume = Volume.LOW;

    @Override
    public Volume getMusicVolume() {
        return musicVolume;
    }

    @Override
    public Volume getEffectsVolume() {
        return effectsVolume;
    }

    @Override
    public void setMusicVolume(Volume volume) {
        this.musicVolume=volume;
    }

    @Override
    public void setEffectsVolume(Volume volume) {
        this.effectsVolume=volume;
    }
}
