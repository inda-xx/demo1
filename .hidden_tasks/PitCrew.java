import java.util.List;
import java.util.ArrayList;

public class PitCrew {

    private static final PitCrew instance = new PitCrew();
    private final ExecutorService pitExecutor;
    private final CopyOnWriteArrayList<PitEventListener> listeners;

    private PitCrew() {
        pitExecutor = Executors.newSingleThreadExecutor();
        listeners = new CopyOnWriteArrayList<>();
    }

    public static PitCrew getInstance() {
        return instance;
    }

    /**
     * Registers a listener to receive pit stop completion events.
     *
     * @param listener The listener to add.
     */
    public void registerListener(PitEventListener listener) {
        listeners.add(listener);
    }

    /**
     * Triggers a pit stop for the given car asynchronously.
     *
     * @param car The car needing a pit stop.
     */
    public void performPitStop(Car car) {
        pitExecutor.submit(() -> {
            System.out.println("BOX BOX! Car " + car.getCarId() + " is pitting...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println("Car " + car.getCarId() + " has exited the pit lane.");
            firePitEvent(car);
        });
    }

    private void firePitEvent(Car car) {
        for (PitEventListener listener : listeners) {
            listener.onPitStopComplete(car);
        }
    }

    /**
     * Shuts down thread resources.
     */
    public void shutdown() {
        pitExecutor.shutdown();
    }
}