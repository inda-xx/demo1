// Tyre.java
import java.util.List;

public class Tyre implements Wearable {
    private final String compound;
    private final List<LapData> lapData;

    public Tyre(String compound) {
        // initialize compound and lapData
    }

    public String getCompound() {
        // return the compound
    }

    public void addLapData(LapData data) {
        // add a LapData entry
    }

    public List<LapData> getAllLapData() {
        // return all lap data entries
    }

    @Override
    public double getDegradation(int lapIndex) {
        // compute and return degradation for the given lap
    }

    public LapData getLapData(int index) {
        // return the LapData at the specified index
    }

    public int totalLaps() {
        // return the total number of laps recorded
    }
}