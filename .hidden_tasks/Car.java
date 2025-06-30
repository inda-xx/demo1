public class Car {
    private Tyre currentTyre;
    private int currentLap;

    public Car(Tyre tyre) {
        this.currentTyre = tyre;
        this.currentLap = 0;
    }

    public void advanceLap() {
        if (currentLap < currentTyre.totalLaps() - 1) {
            currentLap++;
        }
    }

    public boolean isTyreAtRisk() {
        double degradation = currentTyre.getDegradation(currentLap);
        return degradation > 70.0;
    }

    public boolean hasTyreExploded() {
        return currentTyre.getDegradation(currentLap) > 95.0;
    }

    public int getCurrentLap() {
        return currentLap;
    }

    public LapData getCurrentLapData() {
        return currentTyre.getLapData(currentLap);
    }

    public void changeTyre(Tyre newTyre) {
        this.currentTyre = newTyre;
        this.currentLap = 0;
    }
}