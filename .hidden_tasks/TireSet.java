import java.util.Set;

public class TireSet {
    private final TireCompound compound;
    private int lapsUsed;

    public TireSet(TireCompound compound) {
        this.compound = compound;
        this.lapsUsed = 0;
    }

    public double getCurrentLapTime() {
        return compound.getBaseLapTime() + (lapsUsed * compound.getDegradationPerLap());
    }

    public void useForLap() {
        lapsUsed++;
    }

    public boolean needsPitStop() {
        return compound.getGripLevel() - (lapsUsed * 0.1) <= 0.3;
    }

    public TireCompound getCompound() {
        return compound;
    }

    public int getLapsUsed() {
        return lapsUsed;
    }

    @Override
    public String toString() {
        return "TireSet{" +
                "compound=" + compound.getName() +
                ", lapsUsed=" + lapsUsed +
                '}';
    }
}