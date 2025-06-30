public class Track {
    private final String name;
    private final double lengthInKm;
    private final int totalLaps;

    public Track(String name, double lengthInKm, int totalLaps) {
        this.name = name;
        this.lengthInKm = lengthInKm;
        this.totalLaps = totalLaps;
    }

    public String getName() {
        return name;
    }

    public double getLengthInKm() {
        return lengthInKm;
    }

    public int getTotalLaps() {
        return totalLaps;
    }

    @Override
    public String toString() {
        return "Track{" +
                "name='" + name + '\'' +
                ", lengthInKm=" + lengthInKm +
                ", totalLaps=" + totalLaps +
                '}';
    }
}