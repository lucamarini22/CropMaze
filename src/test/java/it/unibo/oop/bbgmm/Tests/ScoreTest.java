package it.unibo.oop.bbgmm.Tests;

import it.unibo.oop.bbgmm.Entity.ScoreList;
import it.unibo.oop.bbgmm.Entity.ScoreListImpl;
import it.unibo.oop.bbgmm.Utilities.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ScoreTest {

    @Test
    public void testWriteInOrder() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST,scoreList.getRanking());

        // Write something to it.
        Pair<String,Integer> giovanni = new Pair<>("Giovanni",45);
        Pair<String,Integer> luca = new Pair<>("Luca",20);

        scoreList.addScore(giovanni);
        scoreList.addScore(luca);

        List<Pair<String,Integer>> list = new ArrayList<>();
        list.add(giovanni);
        list.add(luca);

        Assert.assertEquals(list,scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }

    @Test
    public void testWriteNotInOrder() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST,scoreList.getRanking());

        // Write something to it.
        Pair<String,Integer> giovanni = new Pair<>("Giovanni",45);
        Pair<String,Integer> luca = new Pair<>("Luca",20);
        Pair<String,Integer> mario = new Pair<>("Mario",10);

        scoreList.addScore(luca);
        scoreList.addScore(mario);
        scoreList.addScore(giovanni);

        List<Pair<String,Integer>> list = new ArrayList<>();
        list.add(giovanni);
        list.add(luca);
        list.add(mario);

        Assert.assertEquals(list,scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }

    @Test
    public void testWriteMoreThanFive() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST,scoreList.getRanking());

        // Write something to it.
        Pair<String,Integer> giovanni = new Pair<>("Giovanni",45);
        Pair<String,Integer> luca = new Pair<>("Luca",20);
        Pair<String,Integer> mario = new Pair<>("Mario",10);
        Pair<String,Integer> antonio = new Pair<>("Antonio",6);
        Pair<String,Integer> lucia = new Pair<>("Lucia",30);
        Pair<String,Integer> simone = new Pair<>("Simone",60);

        scoreList.addScore(luca);
        scoreList.addScore(mario);
        scoreList.addScore(giovanni);
        scoreList.addScore(antonio);
        scoreList.addScore(lucia);
        scoreList.addScore(simone);

        List<Pair<String,Integer>> list = new ArrayList<>();
        list.add(simone);
        list.add(giovanni);
        list.add(lucia);
        list.add(luca);
        list.add(mario);

        Assert.assertEquals(list,scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }

    @Test
    public void testWriteSameScore() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST,scoreList.getRanking());

        // Write something to it.
        Pair<String,Integer> giovanni = new Pair<>("Giovanni",45);
        Pair<String,Integer> luca = new Pair<>("Luca",20);
        Pair<String,Integer> mario = new Pair<>("Mario",10);

        scoreList.addScore(luca);
        scoreList.addScore(mario);
        scoreList.addScore(giovanni);
        scoreList.addScore(giovanni);
        scoreList.addScore(luca);

        List<Pair<String,Integer>> list = new ArrayList<>();
        list.add(giovanni);
        list.add(luca);
        list.add(mario);

        Assert.assertEquals(list,scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }
}