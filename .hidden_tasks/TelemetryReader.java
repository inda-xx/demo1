public class TelemetryReader {
    private static final String DELIM = ";";

    /**
     * Parses a single line of telemetry CSV into four Tire objects.
     *
     * @param csv the line to parse
     * @return Tire array in FL, FR, RL, RR order or null if any problem occurs
     */
    public Tire[] parseLine(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return null;
        }

        String[] tokens = csv.split(DELIM);
        if (tokens.length < 12) {
            return null;
        }

        try {
            Tire[] tires = new Tire[4];
            tires[0] = new Tire(Position.FL,
                    Double.parseDouble(tokens[1]),
                    Double.parseDouble(tokens[2]),
                    Double.parseDouble(tokens[3]));

            tires[1] = new Tire(Position.FR,
                    Double.parseDouble(tokens[4]),
                    Double.parseDouble(tokens[5]),
                    Double.parseDouble(tokens[6]));

            tires[2] = new Tire(Position.RL,
                    Double.parseDouble(tokens[7]),
                    Double.parseDouble(tokens[8]),
                    Double.parseDouble(tokens[9]));

            tires[3] = new Tire(Position.RR,
                    Double.parseDouble(tokens[10]),
                    Double.parseDouble(tokens[11]),
                    Double.parseDouble(tokens[12]));

            return tires;
        } catch (NumberFormatException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}