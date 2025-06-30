// File: Car.java
import strategist.model.TireSet;
import strategist.strategy.LapTimeStrategy;

public class Car {
    private TireSet currentTireSet;
    private LapTimeStrategy lapTimeStrategy;

    public Car(TireSet startingTires, LapTimeStrategy strategy) {
        // constructor setup
    }

    public double runLap() {
        // calculate and return lap time
        return 0.0;
    }

    public void pitForNewTires(TireSet newTireSet) {
        // switch to new tires
    }

    public TireSet getCurrentTireSet() {
        // return the current tire set
        return null;
    }

    public void setLapTimeStrategy(LapTimeStrategy strategy) {
        // set a new lap time strategy
    }

    @Override
    public String toString() {
        // return string representation
        return super.toString();
    }
}

// File: strategist/strategy/LapTimeStrategy.java
package strategist.strategy;

import strategist.model.TireCompound;

public interface LapTimeStrategy {
    double calculateLapTime(TireCompound compound, int lapsUsed);
}