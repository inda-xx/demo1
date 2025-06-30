package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LapTimeStrategyTest {

    private final TireCompound compound = new TireCompound("Hard", 95.0, 0.3, 0.95);

    @Test
    public void linearStrategyUsesCorrectFormula() {
        LinearDegradationStrategy linear = new LinearDegradationStrategy();
        assertEquals(95.0, linear.calculateLapTime(compound, 0), 0.0001);
        assertEquals(95.6, linear.calculateLapTime(compound, 2), 0.0001);
    }

    @Test
    public void exponentialStrategyUsesCorrectFormula() {
        ExponentialDegradationStrategy exp = new ExponentialDegradationStrategy();
        double expected = 95.0 * Math.pow(1.01, 5);
        assertEquals(expected, exp.calculateLapTime(compound, 5), 0.000001);
    }

    @Test
    public void exponentialStrategyNeverReturnsLessThanBase() {
        ExponentialDegradationStrategy exp = new ExponentialDegradationStrategy();
        for (int laps = 0; laps < 100; laps++) {
            assertTrue(exp.calculateLapTime(compound, laps) >= compound.getBaseLapTime());
        }
    }
}



// File: CarTest.java
import org.junit.Test;
import static org.junit.Assert.*;

