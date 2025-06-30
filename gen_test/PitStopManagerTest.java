package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PitStopManagerTest {

    @Test
    public void chooseNewTireSetReturnsHighestGripCompound() {
        TireCompound soft = new TireCompound("Soft", 90.0, 0.5, 0.9);
        TireCompound medium = new TireCompound("Medium", 92.0, 0.3, 0.85);
        TireCompound superSoft = new TireCompound("SuperSoft", 88.0, 0.7, 0.95);

        List<TireCompound> available = Arrays.asList(soft, medium, superSoft);
        PitStopManager manager = new PitStopManager(available);

        TireSet chosen = manager.chooseNewTireSet();
        assertEquals("SuperSoft", chosen.getCompound().getName());
    }

    @Test
    public void shouldPitDelegatesToTireSetLogic() {
        TireCompound soft = new TireCompound("Soft", 90.0, 0.5, 0.7);
        TireSet set = new TireSet(soft);
        Car car = new Car(set, new LinearDegradationStrategy());
        PitStopManager manager = new PitStopManager(Arrays.asList(soft));

        // Bring grip below threshold
        for (int i = 0; i < 5; i++) {
            set.useForLap();
        }

        assertTrue(set.needsPitStop());
        assertTrue(manager.shouldPit(car));
    }
}



// File: CsvLoaderTest.java
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

