package it.unibo.oop.bbgmm.tests;

import it.unibo.oop.bbgmm.entity.ScoreList;
import it.unibo.oop.bbgmm.entity.ScoreListImpl;
import it.unibo.oop.bbgmm.utilities.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Test for Ranking using.
 */
public class ScoreTest {

    /**
     * Basic test for Ranking writing on file.
     *
     * @throws IOException
     *          Exception thrown if the file does not exist
     */
    @Test
    public void testWriteInOrder() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST, scoreList.getRanking());

        // Write something to it.
        final Pair<String, Integer> giovanni = new Pair<>("Giovanni", 45);
        final Pair<String, Integer> luca = new Pair<>("Luca", 20);

        scoreList.addScore(giovanni);
        scoreList.addScore(luca);

        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(giovanni);
        list.add(luca);

        Assert.assertEquals(list, scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }

    /**
     * Test for Ranking writing not in order.
     *
     * @throws IOException
     *          Exception thrown if the file does not exist
     */
    @Test
    public void testWriteNotInOrder() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST, scoreList.getRanking());

        // Write something to it.
        final Pair<String, Integer> giovanni = new Pair<>("Giovanni", 45);
        final Pair<String, Integer> luca = new Pair<>("Luca", 20);
        final Pair<String, Integer> mario = new Pair<>("Mario", 10);

        scoreList.addScore(luca);
        scoreList.addScore(mario);
        scoreList.addScore(giovanni);

        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(giovanni);
        list.add(luca);
        list.add(mario);

        Assert.assertEquals(list, scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }

    /**
     * Test for Ranking writing more than max.
     *
     * @throws IOException
     *          Exception thrown if the file does not exist
     */
    @Test
    public void testWriteMoreThanMax() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST, scoreList.getRanking());

        // Write something to it.
        final Pair<String, Integer> giovanni = new Pair<>("Giovanni", 45);
        final Pair<String, Integer> luca = new Pair<>("Luca", 20);
        final Pair<String, Integer> mario = new Pair<>("Mario", 10);
        final Pair<String, Integer> antonio = new Pair<>("Antonio", 6);
        final Pair<String, Integer> lucia = new Pair<>("Lucia", 30);
        final Pair<String, Integer> simone = new Pair<>("Simone", 60);

        scoreList.addScore(luca);
        scoreList.addScore(mario);
        scoreList.addScore(giovanni);
        scoreList.addScore(antonio);
        scoreList.addScore(lucia);
        scoreList.addScore(simone);

        //max is 5
        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(simone);
        list.add(giovanni);
        list.add(lucia);
        list.add(luca);
        list.add(mario);

        Assert.assertEquals(list, scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }

    /**
     * Test for Ranking writing with repetitions.
     *
     * @throws IOException
     *          Exception thrown if the file does not exist
     */
    @Test
    public void testWriteSameScore() throws IOException {
        final ScoreList scoreList = new ScoreListImpl();

        //test file is empty
        scoreList.deleteAll();
        Assert.assertEquals(Collections.EMPTY_LIST, scoreList.getRanking());

        // Write something to it.
        final Pair<String, Integer> giovanni = new Pair<>("Giovanni", 45);
        final Pair<String, Integer> luca = new Pair<>("Luca", 20);
        final Pair<String, Integer> mario = new Pair<>("Mario", 10);

        scoreList.addScore(luca);
        scoreList.addScore(mario);
        scoreList.addScore(giovanni);
        scoreList.addScore(giovanni);
        scoreList.addScore(luca);

        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(giovanni);
        list.add(luca);
        list.add(mario);

        Assert.assertEquals(list, scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }
}