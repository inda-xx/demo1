import java.util.Set;

public class Car {
    private TireSet currentTireSet;
    private LapTimeStrategy lapTimeStrategy;

    public Car(TireSet startingTires, LapTimeStrategy strategy) {
        this.currentTireSet = startingTires;
        this.lapTimeStrategy = strategy;
    }

    public double runLap() {
        double time = lapTimeStrategy.calculateLapTime(currentTireSet.getCompound(), currentTireSet.getLapsUsed());
        currentTireSet.useForLap();
        return time;
    }

    public void pitForNewTires(TireSet newTireSet) {
        this.currentTireSet = newTireSet;
    }

    public TireSet getCurrentTireSet() {
        return currentTireSet;
    }

    public void setLapTimeStrategy(LapTimeStrategy strategy) {
        this.lapTimeStrategy = strategy;
    }

    @Override
    public String toString() {
        return "Car{" +
                "currentTireSet=" + currentTireSet +
                '}';
    }
}
 
// File: strategist/strategy/LapTimeStrategy.java
package strategist.strategy;

import strategist.model.TireCompound;

public interface LapTimeStrategy {
    double calculateLapTime(TireCompound compound, int lapsUsed);
}