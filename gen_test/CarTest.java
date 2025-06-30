package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void equalsAndHashCodeDependOnlyOnCarId() {
        Car car1 = new Car("SAME", 10.0, 80.0);
        Car car2 = new Car("SAME", 99.9, 200.0); // different values but same ID

        assertEquals(car1, car2);
        assertEquals(car1.hashCode(), car2.hashCode());
    }

    @Test
    public void notEqualsForDifferentIds() {
        Car car1 = new Car("CAR1", 10.0, 80.0);
        Car car2 = new Car("CAR2", 10.0, 80.0);

        assertNotEquals(car1, car2);
    }

    @Test
    public void toStringContainsKeyInformation() {
        Car car = new Car("LOOK", 15.0, 90.0);
        String str = car.toString();
        assertTrue(str.contains("LOOK"));
        assertTrue(str.contains("tireWear"));
        assertTrue(str.contains("engineTemp"));
    }
}