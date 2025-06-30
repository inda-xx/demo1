import java.util.List;
import java.util.ArrayList;

public class Tyre implements Wearable {
    private final String compound;
    private final List<LapData> lapData;

    public Tyre(String compound) {
        this.compound = compound;
        this.lapData = new ArrayList<>();
    }

    public String getCompound() {
        return compound;
    }

    public void addLapData(LapData data) {
        lapData.add(data);
    }

    public List<LapData> getAllLapData() {
        return lapData;
    }

    @Override
    public double getDegradation(int lapIndex) {
        if (lapIndex < 0 || lapIndex >= lapData.size()) {
            throw new IllegalArgumentException("Invalid lap index");
        }
        double rawDegradation = lapData.get(lapIndex).getDegradation();
        return Math.min(rawDegradation, 100.0);
    }

    public LapData getLapData(int index) {
        return lapData.get(index);
    }

    public int totalLaps() {
        return lapData.size();
    }
}