package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TelemetryReaderTest {

    private final TelemetryReader reader = new TelemetryReader();

    /* ---------- happy-path tests ---------- */

    @Test
    public void parseLineReturnsFourTiresInCorrectOrder() {
        String csv = "123456;10;90;30;5;80;32;0;60;29;20;70;28";
        Tire[] tires = reader.parseLine(csv);

        assertNotNull(tires);
        assertEquals(4, tires.length);

        assertEquals(Tire.Position.FL, tires[0].getPosition());
        assertEquals(10.0, tires[0].getWear(), 0.0001);
        assertEquals(90.0, tires[0].getTemperature(), 0.0001);
        assertEquals(30.0, tires[0].getPressure(), 0.0001);

        assertEquals(Tire.Position.FR, tires[1].getPosition());
        assertEquals(5.0, tires[1].getWear(), 0.0001);
        assertEquals(80.0, tires[1].getTemperature(), 0.0001);
        assertEquals(32.0, tires[1].getPressure(), 0.0001);

        assertEquals(Tire.Position.RL, tires[2].getPosition());
        assertEquals(0.0, tires[2].getWear(), 0.0001);
        assertEquals(60.0, tires[2].getTemperature(), 0.0001);
        assertEquals(29.0, tires[2].getPressure(), 0.0001);

        assertEquals(Tire.Position.RR, tires[3].getPosition());
        assertEquals(20.0, tires[3].getWear(), 0.0001);
        assertEquals(70.0, tires[3].getTemperature(), 0.0001);
        assertEquals(28.0, tires[3].getPressure(), 0.0001);
    }

    @Test
    public void parseLineIgnoresExtraTokens() {
        String csv = "0;1;20;30;4;40;31;2;60;28;3;80;29;EXTRA_DATA";
        Tire[] tires = reader.parseLine(csv);
        assertNotNull(tires);
        assertEquals(4, tires.length);  // Extra token does not affect parsing
    }

    /* ---------- null / empty / malformed input ---------- */

    @Test
    public void parseLineReturnsNullForNullInput() {
        assertNull(reader.parseLine(null));
    }

    @Test
    public void parseLineReturnsNullForEmptyInput() {
        assertNull(reader.parseLine(""));
        assertNull(reader.parseLine("     "));
    }

    @Test
    public void parseLineReturnsNullWhenNotEnoughTokens() {
        String csv = "123;1;2;3;4;5;6;7;8;9;10;11"; // 12 tokens < 13
        assertNull(reader.parseLine(csv));
    }

    @Test
    public void parseLineReturnsNullWhenNonNumericFound() {
        String csv = "0;A;20;30;4;40;31;2;60;28;3;80;29";
        assertNull(reader.parseLine(csv));
    }

    @Test
    public void parseLineReturnsNullWhenMetricOutOfRange() {
        // wear of front-left tire is 150 (invalid)
        String csv = "0;150;90;30;5;80;32;0;60;29;20;70;28";
        assertNull(reader.parseLine(csv));
    }

    @Test
    public void parseLineReturnsNullWhenPressureNegative() {
        String csv = "0;10;90;-1;5;80;32;0;60;29;20;70;28";
        assertNull(reader.parseLine(csv));
    }
}