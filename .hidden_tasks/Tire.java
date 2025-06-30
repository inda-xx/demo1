public class Tire {

    private final String compound;
    private final int startingGrip;
    private final double degradationPerLap;
    private final int maxLaps;

    public Tire(String compound, int startingGrip, double degradationPerLap, int maxLaps) {
        this.compound = compound;
        this.startingGrip = startingGrip;
        this.degradationPerLap = degradationPerLap;
        this.maxLaps = maxLaps;
    }

    public String getCompound() {
        return compound;
    }

    public int getStartingGrip() {
        return startingGrip;
    }

    public double getDegradationPerLap() {
        return degradationPerLap;
    }

    public int getMaxLaps() {
        return maxLaps;
    }

    @Override
    public String toString() {
        return String.format("Tire{compound='%s', grip=%d%%, degradation=%.2f%%, maxLaps=%d}",
                compound, startingGrip, degradationPerLap, maxLaps);
    }
}