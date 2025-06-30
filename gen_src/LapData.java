// File: LapData.java
public class LapData {
    private final int lapNumber;
    private final double tyreTemperature;
    private final double gripLevel;
    private final double degradation;

    public LapData(int lapNumber, double tyreTemperature, double gripLevel, double degradation) {
        // constructor
    }

    public int getLapNumber() {
        // TODO: return lap number
    }

    public double getTyreTemperature() {
        // TODO: return tyre temperature
    }

    public double getGripLevel() {
        // TODO: return grip level
    }

    public double getDegradation() {
        // TODO: return degradation
    }

    @Override
    public String toString() {
        // TODO: string representation
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: equality check
    }

    @Override
    public int hashCode() {
        // TODO: compute hash code
    }
}

// File: model/Wearable.java
package model;

public interface Wearable {
    double getDegradation(int lapIndex);
}