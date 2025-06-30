import java.util.List;
import java.util.ArrayList;

public class CsvLoader {

    public static List<TireCompound> loadTireCompounds(String filepath) throws IOException {
        List<TireCompound> compounds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean isFirst = true;

            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                String[] tokens = line.split(",");

                if (tokens.length != 4) {
                    throw new IOException("Invalid number of columns in compounds file");
                }

                String name = tokens[0].trim();
                double baseLapTime = Double.parseDouble(tokens[1].trim());
                double degradationPerLap = Double.parseDouble(tokens[2].trim());
                double gripLevel = Double.parseDouble(tokens[3].trim());

                TireCompound compound = new TireCompound(name, baseLapTime, degradationPerLap, gripLevel);
                compounds.add(compound);
            }
        }
        return compounds;
    }

    public static List<Track> loadTracks(String filepath) throws IOException {
        List<Track> tracks = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            boolean isFirst = true;

            while ((line = reader.readLine()) != null) {
                if (isFirst) {
                    isFirst = false;
                    continue;
                }
                String[] tokens = line.split(",");

                if (tokens.length != 3) {
                    throw new IOException("Invalid number of columns in tracks file");
                }

                String name = tokens[0].trim();
                double lengthKm = Double.parseDouble(tokens[1].trim());
                int totalLaps = Integer.parseInt(tokens[2].trim());

                Track track = new Track(name, lengthKm, totalLaps);
                tracks.add(track);
            }
        }
        return tracks;
    }
}