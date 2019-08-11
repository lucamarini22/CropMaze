package it.unibo.oop.bbgmm.boundary;

import it.unibo.oop.bbgmm.entity.ScoreListImpl;

import java.io.IOException;

public class prova {
    public static void main(String[] args) throws IOException {
        new ScoreListImpl().deleteAll();
    }
}
