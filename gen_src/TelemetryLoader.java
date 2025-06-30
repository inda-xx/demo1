// File: TelemetryLoader.java
import java.util.Map;

public class TelemetryLoader {

    /**
     * Parses car telemetry from a given CSV file.
     *
     * @param csvFileName The path to the CSV file.
     * @return A map of Car-ID to Car object.
     */
    public static Map<String, Car> loadCars(String csvFileName) {
        // TODO: read lines, split tokens, handle malformed entries, populate and return the map
        return null;
    }

    // Logs a malformed line with a reason
    private static void logMalformed(String line, String reason) {
        // TODO: implement logging of malformed rows
    }
}