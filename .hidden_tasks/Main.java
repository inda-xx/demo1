import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        try {
            List<TireCompound> compounds = CsvLoader.loadTireCompounds("data/compounds.csv");
            List<Track> tracks = CsvLoader.loadTracks("data/tracks.csv");

            if (compounds.size() < 1 || tracks.size() < 1) {
                throw new RuntimeException("Compounds or tracks data is empty.");
            }

            TireSet startingTires = new TireSet(compounds.get(0));
            Car car = new Car(startingTires, new LinearDegradationStrategy());

            Track track = tracks.get(0);
            PitStopManager pitStopManager = new PitStopManager(compounds);

            System.out.println("Track: " + track.getName());
            System.out.println("Starting Tires: " + car.getCurrentTireSet());

            for (int lap = 1; lap <= track.getTotalLaps(); lap++) {
                double lapTime = car.runLap();
                System.out.printf("Lap %d: %.2f seconds (Tires: %s)\n", lap, lapTime, car.getCurrentTireSet());

                if (pitStopManager.shouldPit(car)) {
                    System.out.println("PIT NOW!");
                    TireSet newSet = pitStopManager.chooseNewTireSet();
                    car.pitForNewTires(newSet);
                }
            }

        } catch (IOException e) {
            System.err.println("Failed to load CSV files: " + e.getMessage());
        }
    }
}