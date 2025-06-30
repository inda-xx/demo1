public class IOManager {

    /**
     * Loads a Tyre with lap data from a telemetry file.
     * @param filePath Path to telemetry file.
     * @param compound Compound name.
     * @return Tyre object with populated lap data.
     */
    public static Tyre loadLapData(String filePath, String compound) {
        Tyre tyre = new Tyre(compound);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.trim().split(",");
                if (tokens.length != 4) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

                try {
                    int lap = Integer.parseInt(tokens[0].trim());
                    double temp = Double.parseDouble(tokens[1].trim());
                    double grip = Double.parseDouble(tokens[2].trim());
                    double degr = Double.parseDouble(tokens[3].trim());

                    tyre.addLapData(new LapData(lap, temp, grip, degr));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping malformed numeric line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load telemetry from file: " + e.getMessage());
        }
        return tyre;
    }
}