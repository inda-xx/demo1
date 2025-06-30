// TireSet.java

import java.util.Set;

public class TireSet {
    private final TireCompound compound;
    private int lapsUsed;

    public TireSet(TireCompound compound) {
        // initialize fields
    }

    public double getCurrentLapTime() {
        return 0.0;
    }

    public void useForLap() {
        // increment laps used
    }

    public boolean needsPitStop() {
        return false;
    }

    public TireCompound getCompound() {
        return null;
    }

    public int getLapsUsed() {
        return 0;
    }

    @Override
    public String toString() {
        return "";
    }
}