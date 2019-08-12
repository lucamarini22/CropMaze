package it.unibo.oop.bbgmm.utilities;

/**
 * Volume values.
 */
public enum Volume {

    /**
     * Mute volume.
     */
    MUTE(0, "MUTE"),

    /**
     * Low volume.
     */
    LOW(0.25, "LOW VOLUME"),

    /**
     * Medium volume.
     */
    MEDIUM(0.5, "MEDIUM VOLUME"),

    /**
     * Max volume.
     */
    MAX(1, "MAX VOLUME");


    private final double value;
    private final String text;
    Volume(final double value, final String text) {
        this.value = value;
        this.text = text;
    }

    /**
     * Return the volume value as double.
     * @return the volume value
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Return the volume value as text.
     * @return the value text
     */
    public String getText() {
        return text;
    }
}
