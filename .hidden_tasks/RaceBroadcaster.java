import java.util.List;

public class RaceBroadcaster implements PitEventListener {

    @Override
    public void onPitStopComplete(Car car) {
        System.out.println("[BROADCAST] Pit stop complete for car " + car.getCarId());
    }
}