package it.unibo.oop.bbgmm.tests;

import it.unibo.oop.bbgmm.entity.BasicScoreCalculator;
import it.unibo.oop.bbgmm.entity.PlayerStatistics;
import it.unibo.oop.bbgmm.entity.PlayerStatisticsImpl;
import it.unibo.oop.bbgmm.entity.ScoreCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

public class ScoreCalculatorTest {
    private final ScoreCalculator scoreCalculator = new BasicScoreCalculator();
    private final PlayerStatistics playerStatistics = new PlayerStatisticsImpl();

    @Test
    public void testCoinCollection() {
        IntStream.range(0,5).forEach(i -> this.playerStatistics.increaseCollectedMoney());
        Assert.assertEquals(this.playerStatistics.getCollectedMoney(), 5);
    }

    @Test
    public void calculateScoreWithOnlyMoney() {
        IntStream.range(0,12).forEach(i -> this.playerStatistics.increaseCollectedMoney());
        Assert.assertEquals(this.scoreCalculator.getScore(this.playerStatistics), 12);
    }

    @Test
    public void testKillingEnemies() {
        IntStream.range(0,10).forEach(i -> this.playerStatistics.increaseKilledEnemies());
        Assert.assertEquals(this.playerStatistics.getKilledEnemies(), 10);
    }

    @Test
    public void calculateScoreWithOnlyKilledEnemies() {
        IntStream.range(0,10).forEach(i -> this.playerStatistics.increaseKilledEnemies());
        Assert.assertEquals(this.scoreCalculator.getScore(this.playerStatistics), 500);
    }

    @Test
    public void calculateScoreWithCoinCollectionAndKilledEnemies() {
        IntStream.range(0,120).forEach(i -> this.playerStatistics.increaseCollectedMoney());
        IntStream.range(0,10).forEach(i -> this.playerStatistics.increaseKilledEnemies());
        Assert.assertEquals(this.scoreCalculator.getScore(this.playerStatistics), 620);
    }
}
