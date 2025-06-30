package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StrategyEngineTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    @Before
    public void redirectStdStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStdStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test(timeout = 2000)
    public void runCompletesAndPrintsRaceEnded() {
        // Build a simple car with a single-lap tyre to keep loop iterations minimal
        Tyre tyre = new Tyre("Soft");
        tyre.addLapData(new LapData(1, 85.0, 0.92, 20.0));
        Car car = new Car(tyre);

        // Provide dummy user input (newline) in case engine asks anything.
        System.setIn(new ByteArrayInputStream("no\n".getBytes()));

        StrategyEngine engine = new StrategyEngine();
        engine.run(car);

        String output = outContent.toString();
        assertTrue("Engine should print end-of-race message", output.contains("Race ended"));
    }
}