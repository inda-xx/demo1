package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TireSetTest {

    private final TireCompound soft = new TireCompound("Soft", 90.0, 0.5, 0.9);

    @Test
    public void initialLapTimeEqualsBaseLapTime() {
        TireSet set = new TireSet(soft);
        assertEquals(90.0, set.getCurrentLapTime(), 0.0001);
    }

    @Test
    public void lapTimeIncreasesLinearlyWithUsage() {
        TireSet set = new TireSet(soft);
        for (int i = 1; i <= 5; i++) {
            set.useForLap();
            assertEquals(90.0 + (0.5 * i), set.getCurrentLapTime(), 0.0001);
        }
    }

    @Test
    public void lapsUsedIncrementsCorrectly() {
        TireSet set = new TireSet(soft);
        assertEquals(0, set.getLapsUsed());
        set.useForLap();
        assertEquals(1, set.getLapsUsed());
    }

    @Test
    public void needsPitStopReturnsFalseWhenGripAboveThreshold() {
        TireSet set = new TireSet(soft); // grip 0.9
        assertFalse(set.needsPitStop()); // 0 laps ⇒ 0.9 – 0.0 = 0.9
    }

    @Test
    public void needsPitStopReturnsTrueWhenGripFallsBelowThreshold() {
        TireCompound medium = new TireCompound("Medium", 92.0, 0.2, 0.8);
        TireSet set = new TireSet(medium);
        for (int i = 0; i < 6; i++) { // 0.8 – 0.6 = 0.2 ≤ 0.3
            set.useForLap();
        }
        assertTrue(set.needsPitStop());
    }

    @Test
    public void toStringContainsCompoundNameAndLapCount() {
        TireSet set = new TireSet(soft);
        set.useForLap();
        String value = set.toString();
        assertTrue(value.contains("Soft"));
        assertTrue(value.contains("1"));
    }
}



// File: LapTimeStrategyTest.java
import org.junit.Test;
import static org.junit.Assert.*;

