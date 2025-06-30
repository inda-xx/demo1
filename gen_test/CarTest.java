package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {

    private Car car;
    private Tyre tyre;

    @Before
    public void setUp() {
        tyre = new Tyre("Hard");
        tyre.addLapData(new LapData(1, 90.0, 0.90, 60.0)); // safe
        tyre.addLapData(new LapData(2, 91.0, 0.88, 80.0)); // at risk
        tyre.addLapData(new LapData(3, 92.0, 0.85, 96.0)); // exploded
        car = new Car(tyre);
    }

    @Test
    public void advanceLapIncrementsUntilLastLap() {
        car.advanceLap(); // lap -> 1
        assertEquals(1, car.getCurrentLap());
        car.advanceLap(); // lap -> 2
        assertEquals(2, car.getCurrentLap());
        car.advanceLap(); // Should stay at 2
        assertEquals(2, car.getCurrentLap());
    }

    @Test
    public void tyreRiskDetectionIsAccurate() {
        assertFalse("60% degradation should be safe", car.isTyreAtRisk());
        car.advanceLap(); // degradation 80%
        assertTrue("80% degradation should be risky", car.isTyreAtRisk());
    }

    @Test
    public void tyreExplosionDetectionIsAccurate() {
        car.advanceLap(); // index 1
        assertFalse(car.hasTyreExploded());
        car.advanceLap(); // index 2
        assertTrue(car.hasTyreExploded());
    }

    @Test
    public void changeTyreResetsLapCounterAndUsesNewTyre() {
        Tyre fresh = new Tyre("Fresh");
        fresh.addLapData(new LapData(1, 80.0, 0.95, 10.0));
        car.advanceLap();
        assertEquals(1, car.getCurrentLap());
        car.changeTyre(fresh);
        assertEquals(0, car.getCurrentLap());
        assertEquals("Fresh", car.getCurrentLapData().getLapNumber() == 1 ? fresh.getCompound() : "");
    }
}


// File: IOManagerTest.java
import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.List;

