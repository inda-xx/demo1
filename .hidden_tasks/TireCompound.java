public class TireCompound {
    private final String name;
    private final double baseLapTime;
    private final double degradationPerLap;
    private final double gripLevel;

    public TireCompound(String name, double baseLapTime, double degradationPerLap, double gripLevel) {
        this.name = name;
        this.baseLapTime = baseLapTime;
        this.degradationPerLap = degradationPerLap;
        this.gripLevel = gripLevel;
    }

    public String getName() {
        return name;
    }

    public double getBaseLapTime() {
        return baseLapTime;
    }

    public double getDegradationPerLap() {
        return degradationPerLap;
    }

    public double getGripLevel() {
        return gripLevel;
    }

    @Override
    public String toString() {
        return "TireCompound{" +
                "name='" + name + '\'' +
                ", baseLapTime=" + baseLapTime +
                ", degradationPerLap=" + degradationPerLap +
                ", gripLevel=" + gripLevel +
                '}';
    }
}