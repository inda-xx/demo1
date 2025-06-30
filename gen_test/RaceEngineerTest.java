package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RaceEngineerTest {

    private final RaceEngineer engineer = new RaceEngineer();

    @Test
    public void needsPitStopReturnsTrueForHighEngineTemp() {
        Car car = new Car("HOT1", 10.0, 200.0); // temp well above threshold
        assertTrue(engineer.needsPitStop(car));
    }

    @Test
    public void needsPitStopReturnsTrueForHighTireWear() {
        Car car = new Car("WORN1", 99.9, 80.0); // wear above threshold
        assertTrue(engineer.needsPitStop(car));
    }

    @Test
    public void needsPitStopReturnsFalseWhenBelowThresholds() {
        Car car = new Car("GOOD1", 10.0, 80.0);
        assertFalse(engineer.needsPitStop(car));
    }

    @Test
    public void needsPitStopReturnsFalseOnExactBoundaryValues() {
        Car carAtTempBoundary = new Car("TMP", 50.0, 105.0); // exactly MAX_TEMP
        Car carAtWearBoundary = new Car("WRN", 75.0, 80.0);  // exactly MAX_TIRE_WEAR
        assertFalse(engineer.needsPitStop(carAtTempBoundary));
        assertFalse(engineer.needsPitStop(carAtWearBoundary));
    }
}

// File: PitCrewTest.java
import org.junit.AfterClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

