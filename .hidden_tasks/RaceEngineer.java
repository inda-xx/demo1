import java.util.List;

public class RaceEngineer {

    private static final double MAX_TEMP = 105.0;
    private static final double MAX_TIRE_WEAR = 75.0;

    /**
     * Evaluates whether a car should pit based on telemetry.
     *
     * @param car The car to evaluate.
     * @return true if pit stop is needed; false otherwise.
     */
    public boolean needsPitStop(Car car) {
        return car.getEngineTemp() > MAX_TEMP || car.getTireWear() > MAX_TIRE_WEAR;
    }
}

// File: PitEventListener.java
/**
 * Interface for classes interested in receiving pit event notifications.
 */
public interface PitEventListener {
    /**
     * Called when a pit stop is completed.
     *
     * @param car Car that just completed a pit stop.
     */
    void onPitStopComplete(Car car);
}