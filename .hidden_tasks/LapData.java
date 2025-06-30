public class LapData {
    private final int lapNumber;
    private final double tyreTemperature;
    private final double gripLevel;
    private final double degradation;

    public LapData(int lapNumber, double tyreTemperature, double gripLevel, double degradation) {
        this.lapNumber = lapNumber;
        this.tyreTemperature = tyreTemperature;
        this.gripLevel = gripLevel;
        this.degradation = degradation;
    }

    public int getLapNumber() {
        return lapNumber;
    }

    public double getTyreTemperature() {
        return tyreTemperature;
    }

    public double getGripLevel() {
        return gripLevel;
    }

    public double getDegradation() {
        return degradation;
    }

    @Override
    public String toString() {
        return String.format("Lap %d | Temp: %.1f°C | Grip: %.1f | Degr: %.1f%%",
                lapNumber, tyreTemperature, gripLevel, degradation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LapData)) return false;
        LapData other = (LapData) obj;
        return lapNumber == other.lapNumber &&
                Double.compare(tyreTemperature, other.tyreTemperature) == 0 &&
                Double.compare(gripLevel, other.gripLevel) == 0 &&
                Double.compare(degradation, other.degradation) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lapNumber, tyreTemperature, gripLevel, degradation);
    }
}
    
// File: model/Wearable.java
package model;

/**
 * Interface for defining wearable components like tyres that degrade over time.
 */
public interface Wearable {
    /**
     * Calculates degradation at a given lap index.
     * @param lapIndex Index in the telemetry list.
     * @return degradation as a percentage.
     */
    double getDegradation(int lapIndex);
}