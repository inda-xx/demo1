public class DemoRace {

    public static void main(String[] args) {
        Tyre softTyre = IOManager.loadLapData("sample_data/tyre_wear_soft.txt", "Soft");
        Car car = new Car(softTyre);

        StrategyEngine engine = new StrategyEngine();
        engine.run(car);
    }
}