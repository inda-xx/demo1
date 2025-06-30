package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TireTest {

    /* ---------- happy-path tests ---------- */

    @Test
    public void constructorAcceptsTypicalValues() {
        Tire tire = new Tire(Tire.Position.FL, 50.0, 100.0, 32.0);
        assertEquals(Tire.Position.FL, tire.getPosition());
        assertEquals(50.0, tire.getWear(), 0.0001);
        assertEquals(100.0, tire.getTemperature(), 0.0001);
        assertEquals(32.0, tire.getPressure(), 0.0001);
    }

    @Test
    public void constructorAcceptsLowerBoundaryValues() {
        Tire tire = new Tire(Tire.Position.FR, 0.0, 20.0, 0.0);
        assertEquals(0.0, tire.getWear(), 0.0001);
        assertEquals(20.0, tire.getTemperature(), 0.0001);
        assertEquals(0.0, tire.getPressure(), 0.0001);
    }

    @Test
    public void constructorAcceptsUpperBoundaryValues() {
        Tire tire = new Tire(Tire.Position.RL, 100.0, 140.0, 100.0);
        assertEquals(100.0, tire.getWear(), 0.0001);
        assertEquals(140.0, tire.getTemperature(), 0.0001);
        assertEquals(100.0, tire.getPressure(), 0.0001);
    }

    /* ---------- validation tests ---------- */

    @Test
    public void constructorRejectsWearBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(Tire.Position.RR, -0.1, 100.0, 32.0));
    }

    @Test
    public void constructorRejectsWearAboveRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(Tire.Position.FL, 100.1, 100.0, 32.0));
    }

    @Test
    public void constructorRejectsTemperatureBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(Tire.Position.FR, 50.0, 19.9, 32.0));
    }

    @Test
    public void constructorRejectsTemperatureAboveRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(Tire.Position.RL, 50.0, 140.1, 32.0));
    }

    @Test
    public void constructorRejectsPressureBelowRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(Tire.Position.FL, 50.0, 100.0, -0.1));
    }

    @Test
    public void constructorRejectsPressureAboveRange() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(Tire.Position.FR, 50.0, 100.0, 100.1));
    }

    @Test
    public void constructorRejectsNullPosition() {
        assertThrows(IllegalArgumentException.class,
                () -> new Tire(null, 50.0, 100.0, 32.0));
    }

    /* ---------- toString sanity check ---------- */

    @Test
    public void toStringContainsAllMetrics() {
        Tire tire = new Tire(Tire.Position.FL, 12.3, 45.6, 78.9);
        String repr = tire.toString();
        assertTrue(repr.contains("FL"));
        assertTrue(repr.contains("12.3"));
        assertTrue(repr.contains("45.6"));
        assertTrue(repr.contains("78.9"));
    }
}

// TelemetryReaderTest.java
import org.junit.Test;
import static org.junit.Assert.*;

