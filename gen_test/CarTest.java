package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {

    private final TireCompound soft = new TireCompound("Soft", 90.0, 0.5, 0.9);

    @Test
    public void runLapReturnsValueFromStrategyAndUpdatesUsage() {
        TireSet set = new TireSet(soft);
        LinearDegradationStrategy strat = new LinearDegradationStrategy();
        Car car = new Car(set, strat);

        double lap1 = car.runLap();
        assertEquals(90.0, lap1, 0.0001);
        assertEquals(1, car.getCurrentTireSet().getLapsUsed());

        double lap2 = car.runLap();
        assertEquals(90.5, lap2, 0.0001);
        assertEquals(2, car.getCurrentTireSet().getLapsUsed());
    }

    @Test
    public void pitForNewTiresReplacesTireSet() {
        TireSet oldSet = new TireSet(soft);
        Car car = new Car(oldSet, new LinearDegradationStrategy());

        TireCompound hard = new TireCompound("Hard", 95.0, 0.2, 1.0);
        TireSet newSet = new TireSet(hard);
        car.pitForNewTires(newSet);

        assertSame(newSet, car.getCurrentTireSet());
        assertEquals("Hard", car.getCurrentTireSet().getCompound().getName());
    }

    @Test
    public void canSwitchStrategiesAtRuntime() {
        TireSet set = new TireSet(soft);
        Car car = new Car(set, new LinearDegradationStrategy());

        double linearLap = car.runLap(); // 90.0
        car.setLapTimeStrategy(new ExponentialDegradationStrategy());
        double expLap = car.runLap(); // 90.0 * 1.01^1 = 90.9

        assertEquals(90.0, linearLap, 0.0001);
        assertEquals(90.9, expLap, 0.0001);
    }
}



// File: PitStopManagerTest.java
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

