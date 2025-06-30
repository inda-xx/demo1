public class LinearDegradationStrategy implements LapTimeStrategy {

    @Override
    public double calculateLapTime(TireCompound compound, int lapsUsed) {
        return compound.getBaseLapTime() + (compound.getDegradationPerLap() * lapsUsed);
    }
}