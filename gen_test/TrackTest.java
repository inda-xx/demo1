package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TrackTest {

    @Test
    public void constructorAndGettersStoreValuesCorrectly() {
        Track track = new Track("Monaco", 3.337, 78);
        assertEquals("Monaco", track.getName());
        assertEquals(3.337, track.getLengthInKm(), 0.0001);
        assertEquals(78, track.getTotalLaps());
    }

    @Test
    public void toStringContainsAllFields() {
        Track track = new Track("Spa", 7.004, 44);
        String value = track.toString();
        assertTrue(value.contains("Spa"));
        assertTrue(value.contains("7.004"));
        assertTrue(value.contains("44"));
    }

    @Test
    public void negativeValuesAreStoredUnmodified() {
        Track track = new Track("Faulty", -1.0, -10);
        assertEquals(-1.0, track.getLengthInKm(), 0.0001);
        assertEquals(-10, track.getTotalLaps());
    }
}



// File: TireSetTest.java
import org.junit.Test;
import static org.junit.Assert.*;

