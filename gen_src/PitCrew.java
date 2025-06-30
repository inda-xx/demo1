// File: PitCrew.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CopyOnWriteArrayList;

public class PitCrew {
    private static final PitCrew instance = new PitCrew();
    private final ExecutorService pitExecutor;
    private final CopyOnWriteArrayList<PitEventListener> listeners;

    private PitCrew() {
        // constructor
    }

    public static PitCrew getInstance() {
        // return singleton instance
    }

    public void registerListener(PitEventListener listener) {
        // add a listener
    }

    public void performPitStop(Car car) {
        // submit pit stop task
    }

    private void firePitEvent(Car car) {
        // notify listeners
    }

    public void shutdown() {
        // clean up resources
    }
}