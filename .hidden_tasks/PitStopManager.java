import java.util.List;
import java.util.Set;

public class PitStopManager {
    private final List<TireCompound> availableCompounds;

    public PitStopManager(List<TireCompound> compounds) {
        this.availableCompounds = compounds;
    }

    public boolean shouldPit(Car car) {
        return car.getCurrentTireSet().needsPitStop();
    }

    public TireSet chooseNewTireSet() {
        TireCompound preferred = availableCompounds.get(0);
        for (TireCompound compound : availableCompounds) {
            if (compound.getGripLevel() > preferred.getGripLevel()) {
                preferred = compound;
            }
        }
        return new TireSet(preferred);
    }
}