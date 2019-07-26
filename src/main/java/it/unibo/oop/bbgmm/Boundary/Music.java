package it.unibo.oop.bbgmm.Boundary;

public enum Music {

    TRACK1(""),


    TRACK2("");

    private final String path;

    Music(final String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
