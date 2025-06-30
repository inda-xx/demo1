package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TelemetryLoaderTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void loadCarsParsesValidLinesAndSkipsMalformedOnes() throws IOException {
        File csv = tempFolder.newFile("telemetry.csv");
        List<String> lines = List.of(
                "  C1 , 10.5 , 90.0 ",  // valid with extra whitespace
                "C2,76,110",            // valid – will need pit stop
                "C1,99,130",            // duplicate id – must be ignored
                "C3,notANumber,100",    // malformed – bad number
                "onlytwo,fields",       // malformed – wrong field count
                "C4,40,85"              // another valid car
        );
        Files.write(csv.toPath(), lines, StandardCharsets.UTF_8);

        Map<String, Car> result = TelemetryLoader.loadCars(csv.getAbsolutePath());

        // Only unique valid cars should be in the result: C1, C2, C4
        assertEquals(3, result.size());

        // Validate the first occurrence was kept for duplicates
        Car c1 = result.get("C1");
        assertNotNull(c1);
        assertEquals(10.5, c1.getTireWear(), 0.0001);
        assertEquals(90.0, c1.getEngineTemp(), 0.0001);

        // Validate another valid entry
        Car c2 = result.get("C2");
        assertNotNull(c2);
        assertEquals(76.0, c2.getTireWear(), 0.0001);
        assertEquals(110.0, c2.getEngineTemp(), 0.0001);

        // Validate the last valid entry
        assertTrue(result.containsKey("C4"));
    }

    @Test
    public void loadCarsReturnsEmptyMapWhenFileDoesNotExist() {
        Map<String, Car> result = TelemetryLoader.loadCars("some_file_that_does_not_exist_123.csv");
        assertTrue(result.isEmpty());
    }
}

// File: RaceEngineerTest.java
import org.junit.Test;

import static org.junit.Assert.*;

