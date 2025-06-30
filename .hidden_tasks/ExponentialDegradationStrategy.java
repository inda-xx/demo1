public class ExponentialDegradationStrategy implements LapTimeStrategy {

    @Override
    public double calculateLapTime(TireCompound compound, int lapsUsed) {
        return compound.getBaseLapTime() * Math.pow(1.01, lapsUsed);
    }
}