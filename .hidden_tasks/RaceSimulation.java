import java.util.List;
import java.util.Map;

public class RaceSimulation {

    public static void main(String[] args) {
        Map<String, Car> carMap = TelemetryLoader.loadCars("telemetry.csv");
        RaceEngineer engineer = new RaceEngineer();
        PitCrew pitCrew = PitCrew.getInstance();
        RaceBroadcaster broadcaster = new RaceBroadcaster();

        pitCrew.registerListener(broadcaster);

        for (Car car : carMap.values()) {
            if (engineer.needsPitStop(car)) {
                pitCrew.performPitStop(car);
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        pitCrew.shutdown();
    }
}