// File: Tire.java
public class Tire {
    public enum Position {
        FL, FR, RL, RR
    }

    private final Position position;
    private final double wear;
    private final double temperature;
    private final double pressure;

    public Tire(Position position, double wear, double temperature, double pressure) {
        // TODO: validate parameters and assign to fields
    }

    public double getWear() {
        // TODO: return wear
        return 0;
    }

    public double getTemperature() {
        // TODO: return temperature
        return 0;
    }

    public double getPressure() {
        // TODO: return pressure
        return 0;
    }

    public Position getPosition() {
        // TODO: return position
        return null;
    }

    @Override
    public String toString() {
        // TODO: return formatted string representation
        return null;
    }
}