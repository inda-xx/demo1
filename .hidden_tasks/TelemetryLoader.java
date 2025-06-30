import java.util.Map;
import java.util.HashMap;

public class TelemetryLoader {

    /**
     * Parses car telemetry from a given CSV file.
     * Each line format: CAR_ID,TIRE_WEAR,ENGINE_TEMP
     *
     * @param csvFileName The path to the CSV file.
     * @return A map of Car-ID to Car object.
     */
    public static Map<String, Car> loadCars(String csvFileName) {
        Map<String, Car> cars = new HashMap<>();
        int malformedCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.trim().split(",");

                if (tokens.length != 3) {
                    logMalformed(line, "Incorrect number of fields");
                    malformedCount++;
                    continue;
                }

                try {
                    String carId = tokens[0].trim();
                    double wear = Double.parseDouble(tokens[1].trim());
                    double temp = Double.parseDouble(tokens[2].trim());

                    if (!cars.containsKey(carId)) {
                        cars.put(carId, new Car(carId, wear, temp));
                    }
                } catch (NumberFormatException e) {
                    logMalformed(line, "Invalid number format");
                    malformedCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading telemetry file: " + e.getMessage());
        }

        if (malformedCount >= 3) {
            System.out.println("Logged " + malformedCount + " malformed rows.");
        }

        return cars;
    }

    private static void logMalformed(String line, String reason) {
        System.err.println("Malformed row skipped (" + reason + "): " + line);
    }
}