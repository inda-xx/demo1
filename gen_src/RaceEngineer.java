// File: RaceEngineer.java
public class RaceEngineer {
    private static final double MAX_TEMP = 0.0;
    private static final double MAX_TIRE_WEAR = 0.0;

    public boolean needsPitStop(Car car) {
        // TODO: implement pit stop logic
        return false;
    }
}

// File: PitEventListener.java
public interface PitEventListener {
    void onPitStopComplete(Car car);
}