// File: LapTimeStrategy.java
public interface LapTimeStrategy {
    double calculateLapTime(TireCompound compound, int lapsUsed);
}

// File: LinearDegradationStrategy.java
public class LinearDegradationStrategy implements LapTimeStrategy {

    @Override
    public double calculateLapTime(TireCompound compound, int lapsUsed) {
        // implementation goes here
        return 0.0;
    }
}