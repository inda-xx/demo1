package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CsvLoaderTest {

    private Path createTempFile(String contents) throws IOException {
        Path path = Files.createTempFile("test-csv", ".csv");
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write(contents);
        }
        return path;
    }

    @Test
    public void loadTireCompoundsParsesValidFile() throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("name,base,degradation,grip\n");
        sb.append("Soft,90.0,0.5,0.9\n");
        sb.append("Hard,95.0,0.2,0.95\n");

        Path file = createTempFile(sb.toString());
        List<TireCompound> compounds = CsvLoader.loadTireCompounds(file.toString());

        assertEquals(2, compounds.size());
        TireCompound first = compounds.get(0);
        assertEquals("Soft", first.getName());
        assertEquals(90.0, first.getBaseLapTime(), 0.0001);
        assertEquals(0.5, first.getDegradationPerLap(), 0.0001);
        assertEquals(0.9, first.getGripLevel(), 0.0001);
    }

    @Test(expected = IOException.class)
    public void loadTireCompoundsThrowsOnInvalidColumnCount() throws IOException {
        String content = "name,base,degradation\nSoft,90.0,0.5\n"; // only 3 columns
        Path file = createTempFile(content);
        CsvLoader.loadTireCompounds(file.toString());
    }

    @Test
    public void loadTracksParsesValidFile() throws IOException {
        String content = "name,length,laps\nMonaco,3.337,78\n";
        Path file = createTempFile(content);
        List<Track> tracks = CsvLoader.loadTracks(file.toString());
        assertEquals(1, tracks.size());
        Track monaco = tracks.get(0);
        assertEquals("Monaco", monaco.getName());
        assertEquals(3.337, monaco.getLengthInKm(), 0.0001);
        assertEquals(78, monaco.getTotalLaps());
    }

    @Test(expected = IOException.class)
    public void loadTracksThrowsOnNonNumericValues() throws IOException {
        String content = "name,length,laps\nMonaco,NaN,78\n";
        Path file = createTempFile(content);
        CsvLoader.loadTracks(file.toString());
    }

    @Test
    public void canLoadLargeNumberOfCompoundsEfficiently() throws IOException {
        StringBuilder sb = new StringBuilder("name,base,degradation,grip\n");
        for (int i = 0; i < 1000; i++) {
            sb.append("C").append(i).append(",90.0,0.5,0.9\n");
        }
        Path file = createTempFile(sb.toString());
        List<TireCompound> compounds = CsvLoader.loadTireCompounds(file.toString());
        assertEquals(1000, compounds.size());
    }
}