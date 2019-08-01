package it.unibo.oop.bbgmm.Utilities;

public enum Volume {

    MUTE (0, "MUTE"),

    LOW (0.25, "LOW VOLUME"),

    MEDIUM(0.5, "MEDIUM VOLUME"),

    MAX(1, "MAX VOLUME");


    private final double value;
    private final String text;
    Volume(final double value, final String text) {
        this.value=value;
        this.text=text;
    }

    public double getValue(){
        return this.value;
    }

    public String getText(){
        return text;
    }
}
