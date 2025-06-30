// File: Car.java
public class Car {
    private Tyre currentTyre;
    private int currentLap;

    public Car(Tyre tyre) {
        // initialize tyre and lap
    }

    public void advanceLap() {
        // increment lap if possible
    }

    public boolean isTyreAtRisk() {
        // determine if tyre degradation is above risk threshold
    }

    public boolean hasTyreExploded() {
        // determine if tyre degradation exceeds explosion threshold
    }

    public int getCurrentLap() {
        // return the current lap number
    }

    public LapData getCurrentLapData() {
        // retrieve data for the current lap
    }

    public void changeTyre(Tyre newTyre) {
        // replace the current tyre and reset lap count
    }
}