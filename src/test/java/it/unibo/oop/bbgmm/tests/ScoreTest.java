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
    private static final Pair<String, Integer> GIOVANNI = new Pair<>("Giovanni", 45);
    private static final Pair<String, Integer> LUCA = new Pair<>("Luca", 20);
    private static final Pair<String, Integer> MARIO = new Pair<>("Mario", 10);
    private static final Pair<String, Integer> ANTONIO = new Pair<>("Antonio", 6);
    private static final Pair<String, Integer> LUCIA = new Pair<>("Lucia", 30);
    private static final Pair<String, Integer> SIMONE = new Pair<>("Simone", 60);
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
        scoreList.addScore(GIOVANNI);
        scoreList.addScore(LUCA);

        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(GIOVANNI);
        list.add(LUCA);

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
        scoreList.addScore(LUCA);
        scoreList.addScore(MARIO);
        scoreList.addScore(GIOVANNI);

        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(GIOVANNI);
        list.add(LUCA);
        list.add(MARIO);

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
        scoreList.addScore(LUCA);
        scoreList.addScore(MARIO);
        scoreList.addScore(GIOVANNI);
        scoreList.addScore(ANTONIO);
        scoreList.addScore(LUCIA);
        scoreList.addScore(SIMONE);

        //max is 5
        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(SIMONE);
        list.add(GIOVANNI);
        list.add(LUCIA);
        list.add(LUCA);
        list.add(MARIO);

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
        scoreList.addScore(LUCA);
        scoreList.addScore(MARIO);
        scoreList.addScore(GIOVANNI);
        scoreList.addScore(GIOVANNI);
        scoreList.addScore(LUCA);

        final List<Pair<String, Integer>> list = new ArrayList<>();
        list.add(GIOVANNI);
        list.add(LUCA);
        list.add(MARIO);

        Assert.assertEquals(list, scoreList.getRanking());

        //delete all
        scoreList.deleteAll();
    }
}