// File: LapTimeStrategy.java
public interface LapTimeStrategy {
    double calculateLapTime(TireCompound compound, int lapsUsed);
}

// File: ExponentialDegradationStrategy.java
public class ExponentialDegradationStrategy implements LapTimeStrategy {

    @Override
    public double calculateLapTime(TireCompound compound, int lapsUsed) {
        // TODO: implement exponential degradation calculation
        return 0;
    }
}