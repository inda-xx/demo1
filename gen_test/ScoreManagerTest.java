package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScoreManagerTest {

    @Test
    public void testInitialScoreIsZero() {
        ScoreManager manager = new ScoreManager();
        assertEquals(0, manager.getScore());
    }

    @Test
    public void testAddPointsIncreasesScore() {
        ScoreManager manager = new ScoreManager();
        manager.addPoints(10);
        assertEquals(10, manager.getScore());

        manager.addPoints(5);
        assertEquals(15, manager.getScore());
    }

    @Test
    public void testAddZeroPointsLeavesScoreUnchanged() {
        ScoreManager manager = new ScoreManager();
        manager.addPoints(0);
        assertEquals(0, manager.getScore());
    }

    @Test
    public void testAddNegativePointsReducesScore() {
        ScoreManager manager = new ScoreManager();
        manager.addPoints(10);
        manager.addPoints(-5);
        assertEquals(5, manager.getScore());
    }
}

// File: GameStateManagerTest.java
import org.junit.Test;

import static org.junit.Assert.*;

