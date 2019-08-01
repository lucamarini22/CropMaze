package it.unibo.oop.bbgmm.Control;

import it.unibo.oop.bbgmm.Utilities.Volume;
import it.unibo.oop.bbgmm.Utilities.VolumeDataImpl;

public class SettingsControllerImpl implements SettingController {

    private final VolumeDataImpl volumeData;

    public SettingsControllerImpl(VolumeDataImpl volumeData){
        this.volumeData=volumeData;
    }

    @Override
    public void setMusicVolume(Volume volume) {
        this.volumeData.setMusicVolume(volume);
    }

    @Override
    public void setEffectsVolume(Volume volume) {
        this.volumeData.setEffectsVolume(volume);
    }
}
