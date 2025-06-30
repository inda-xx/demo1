// Main.java
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Load compounds and tracks
            // Validate loaded data
            // Initialize car and pit stop manager
            // Loop over laps and handle pit stops
        } catch (IOException e) {
            // Handle CSV loading errors
        }
    }
}


// CsvLoader.java
import java.io.IOException;
import java.util.List;

public class CsvLoader {
    public static List<TireCompound> loadTireCompounds(String filePath) throws IOException {
        return null;
    }

    public static List<Track> loadTracks(String filePath) throws IOException {
        return null;
    }
}


// TireCompound.java
public class TireCompound {
    // fields and constructors
}


// Track.java
public class Track {
    public String getName() {
        return null;
    }

    public int getTotalLaps() {
        return 0;
    }
}


// TireSet.java
public class TireSet {
    public TireSet(TireCompound compound) {
    }

    @Override
    public String toString() {
        return null;
    }
}


// DegradationStrategy.java
public interface DegradationStrategy {
    // define strategy methods
}


// LinearDegradationStrategy.java
public class LinearDegradationStrategy implements DegradationStrategy {
    // strategy implementation
}


// Car.java
public class Car {
    public Car(TireSet startingTires, DegradationStrategy strategy) {
    }

    public TireSet getCurrentTireSet() {
        return null;
    }

    public double runLap() {
        return 0;
    }

    public void pitForNewTires(TireSet newSet) {
    }
}


// PitStopManager.java
import java.util.List;

public class PitStopManager {
    public PitStopManager(List<TireCompound> compounds) {
    }

    public boolean shouldPit(Car car) {
        return false;
    }

    public TireSet chooseNewTireSet() {
        return null;
    }
}