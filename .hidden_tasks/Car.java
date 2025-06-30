public class Car {
    private final String carId;
    private final double tireWear;
    private final double engineTemp;

    /**
     * Constructs a Car instance based on telemetry data.
     *
     * @param carId       Unique identifier for the car.
     * @param tireWear    Current tire wear (0-100).
     * @param engineTemp  Engine temperature in °C.
     */
    public Car(String carId, double tireWear, double engineTemp) {
        this.carId = carId;
        this.tireWear = tireWear;
        this.engineTemp = engineTemp;
    }

    public String getCarId() {
        return carId;
    }

    public double getTireWear() {
        return tireWear;
    }

    public double getEngineTemp() {
        return engineTemp;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car other)) return false;
        return Objects.equals(this.carId, other.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }

    @Override
    public String toString() {
        return "Car[ID=" + carId + ", tireWear=" + tireWear + ", engineTemp=" + engineTemp + "]";
    }
}