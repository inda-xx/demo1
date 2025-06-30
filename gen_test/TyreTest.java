package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TyreTest {

    private Tyre tyre;

    @Before
    public void setUp() {
        tyre = new Tyre("Soft");
        tyre.addLapData(new LapData(1, 85.0, 0.90, 30.0)); // index 0
        tyre.addLapData(new LapData(2, 86.0, 0.88, 70.0)); // index 1
        tyre.addLapData(new LapData(3, 87.0, 0.85, 100.0)); // index 2 – raw 100
        tyre.addLapData(new LapData(4, 88.0, 0.82, 150.0)); // index 3 – raw 150 → clamp 100
    }

    @Test
    public void totalLapsReturnsCorrectCount() {
        assertEquals(4, tyre.totalLaps());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDegradationThrowsForNegativeIndex() {
        tyre.getDegradation(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDegradationThrowsForIndexEqualToSize() {
        tyre.getDegradation(tyre.totalLaps());
    }

    @Test
    public void getDegradationReturnsUnclampedForValidValuesBelowHundred() {
        assertEquals(30.0, tyre.getDegradation(0), 0.0001);
        assertEquals(70.0, tyre.getDegradation(1), 0.0001);
    }

    @Test
    public void getDegradationClampsValuesAboveHundred() {
        assertEquals(100.0, tyre.getDegradation(2), 0.0001);
        assertEquals(100.0, tyre.getDegradation(3), 0.0001);
    }

    @Test
    public void addLapDataStoresAndRetrievesCorrectly() {
        LapData expected = new LapData(5, 89.0, 0.80, 40.0);
        tyre.addLapData(expected);
        LapData actual = tyre.getLapData(tyre.totalLaps() - 1);
        assertEquals(expected, actual);
    }

    @Test(timeout = 2000)
    public void handlesLargeTelemetryDataSetsEfficiently() {
        Tyre hugeTyre = new Tyre("Medium");
        int laps = 150_000;
        for (int i = 0; i < laps; i++) {
            hugeTyre.addLapData(new LapData(i + 1, 90.0, 0.75, (i % 100)));
        }
        // Quick spot-check
        assertEquals(laps, hugeTyre.totalLaps());
        assertEquals(100.0, hugeTyre.getDegradation(99_999), 0.0); // raw 99 → not clamped
    }
}


// File: CarTest.java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

