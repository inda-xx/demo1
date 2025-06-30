// DemoRace.java
public class DemoRace {
    public static void main(String[] args) {
        Tyre softTyre = IOManager.loadLapData("sample_data/tyre_wear_soft.txt", "Soft");
        Car car = new Car(softTyre);
        StrategyEngine engine = new StrategyEngine();
        engine.run(car);
    }
}

// IOManager.java
public class IOManager {
    public static Tyre loadLapData(String filePath, String tyreType) {
        // TODO: read data and construct a Tyre
        return null;
    }
}

// Tyre.java
public class Tyre {
    // properties and constructor(s) go here
}

// Car.java
public class Car {
    public Car(Tyre tyre) {
        // initialize car with given tyre
    }
    // other methods as needed
}

// StrategyEngine.java
public class StrategyEngine {
    public void run(Car car) {
        // TODO: implement strategy logic
    }
    // additional methods as needed
}