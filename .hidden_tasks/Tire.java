public class Tire {
    public enum Position {
        FL, FR, RL, RR
    }

    private final Position position;
    private final double wear;
    private final double temperature;
    private final double pressure;

    /**
     * Constructs a Tire object with validated metrics.
     *
     * @param position    The position of the tire (FL, FR, RL, RR)
     * @param wear        The wear percentage (0 to 100)
     * @param temperature The temperature in °C (20 to 140)
     * @param pressure    The tire pressure in PSI (0.0 to 100.0 for simplicity)
     * @throws IllegalArgumentException if any values are out of acceptable range
     */
    public Tire(Position position, double wear, double temperature, double pressure) {
        if (wear < 0 || wear > 100) {
            throw new IllegalArgumentException("Wear out of range: " + wear);
        }
        if (temperature < 20 || temperature > 140) {
            throw new IllegalArgumentException("Temperature out of range: " + temperature);
        }
        if (pressure < 0 || pressure > 100) {
            throw new IllegalArgumentException("Pressure out of range: " + pressure);
        }
        if (position == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }

        this.position = position;
        this.wear = wear;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public double getWear() {
        return wear;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return String.format("[%s] Wear: %.1f%%, Temp: %.1f°C, Pressure: %.1f PSI",
                position.name(), wear, temperature, pressure);
    }
}