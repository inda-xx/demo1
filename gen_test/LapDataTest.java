package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LapDataTest {

    @Test
    public void equalsAndHashCodeWorkForIdenticalObjects() {
        LapData a = new LapData(5, 90.0, 0.80, 25.0);
        LapData b = new LapData(5, 90.0, 0.80, 25.0);

        assertEquals("Objects with same state should be equal", a, b);
        assertEquals("Hash codes must match for equal objects", a.hashCode(), b.hashCode());
    }

    @Test
    public void equalsReturnsFalseForDifferentObjects() {
        LapData a = new LapData(5, 90.0, 0.80, 25.0);
        LapData b = new LapData(6, 91.0, 0.78, 26.0);

        assertNotEquals(a, b);
    }

    @Test
    public void toStringContainsAllKeyInformation() {
        LapData data = new LapData(3, 88.5, 0.85, 18.2);
        String str = data.toString();

        assertTrue(str.contains("Lap 3"));
        assertTrue(str.contains("88.5"));
        assertTrue(str.contains("0.9") == false); // ensure we don’t accidentally match wrong numbers
        assertTrue(str.contains("18.2"));
    }
}


// File: TyreTest.java
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

