// File: PitStopManager.java
import java.util.List;

public class PitStopManager {
    private final List<TireCompound> availableCompounds;

    public PitStopManager(List<TireCompound> compounds) {
        this.availableCompounds = compounds;
    }

    public boolean shouldPit(Car car) {
        // TODO: determine if the car needs a pit stop
        return false;
    }

    public TireSet chooseNewTireSet() {
        // TODO: select and return a new TireSet
        return null;
    }
}