package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IOManagerTest {

    private Path createSampleTelemetryFile() throws IOException {
        String header = "lap,temp,grip,degr\n";
        String valid1 = "1,85.0,0.90,10.0\n";
        String valid2 = "2,87.0,0.88,20.0\n";
        String invalidTokenCount = "this,is,bad\n";
        String malformedNumeric = "3,90.0,0.80,notANumber\n";
        String overHundred = "4,91.0,0.75,105.0\n";
        Path temp = Files.createTempFile("telemetry", ".txt");
        Files.writeString(temp, header + valid1 + valid2 + invalidTokenCount + malformedNumeric + overHundred);
        temp.toFile().deleteOnExit();
        return temp;
    }

    @Test
    public void loadLapDataParsesValidLinesAndSkipsInvalidOnes() throws IOException {
        Path file = createSampleTelemetryFile();
        Tyre tyre = IOManager.loadLapData(file.toString(), "Soft");

        // Only 3 valid lines should be parsed.
        assertEquals(3, tyre.totalLaps());

        // Validate content of first and last parsed entries
        LapData first = tyre.getLapData(0);
        assertEquals(1, first.getLapNumber());
        assertEquals(10.0, first.getDegradation(), 0.0001);

        LapData last = tyre.getLapData(2);
        assertEquals(4, last.getLapNumber());
        // Degradation greater than 100 should be clamped
        assertEquals(100.0, tyre.getDegradation(2), 0.0001);
    }
}


// File: StrategyEngineTest.java
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

