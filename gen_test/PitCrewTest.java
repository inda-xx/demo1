package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PitCrewTest {

    private static final PitCrew pitCrew = PitCrew.getInstance();

    @AfterClass
    public static void tearDown() {
        // Ensure that thread resources are cleaned up after all tests in this class.
        pitCrew.shutdown();
    }

    @Test
    public void listenerIsNotifiedWhenPitStopCompletes() throws InterruptedException {
        Car car = new Car("BOX1", 80.0, 110.0);
        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Car> observed = new AtomicReference<>();

        PitEventListener listener = c -> {
            observed.set(c);
            latch.countDown();
        };

        pitCrew.registerListener(listener);
        pitCrew.performPitStop(car);

        boolean completed = latch.await(5, TimeUnit.SECONDS);
        assertTrue("Pit stop did not complete within timeout", completed);
        assertEquals("Listener received wrong car instance", car, observed.get());
    }

    @Test
    public void multipleListenersReceiveCallback() throws InterruptedException {
        Car car = new Car("BOX2", 90.0, 120.0);
        CountDownLatch latch = new CountDownLatch(2);

        PitEventListener listener1 = c -> latch.countDown();
        PitEventListener listener2 = c -> latch.countDown();

        pitCrew.registerListener(listener1);
        pitCrew.registerListener(listener2);

        pitCrew.performPitStop(car);

        assertTrue("Not all listeners were notified", latch.await(5, TimeUnit.SECONDS));
    }
}

// File: CarTest.java
import org.junit.Test;

import static org.junit.Assert.*;

