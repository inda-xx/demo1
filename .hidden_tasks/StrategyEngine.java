import java.util.Scanner;

public class StrategyEngine {

    private final Scanner scanner;
    private boolean replaced = false;

    public StrategyEngine() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Executes the strategy loop.
     * @param car The car being driven.
     */
    public void run(Car car) {
        while (car.getCurrentLap() < car.getCurrentLapData().getLapNumber()) {
            System.out.println(car.getCurrentLapData());

            if (car.hasTyreExploded() && !replaced) {
                System.out.println("💥 Tyre exploded! You are out of the race.");
                replaced = true;
                break;
            }

            if (car.isTyreAtRisk() && !replaced) {
                System.out.print("⚠️ Tyre at risk! Do you want to box? (yes/no): ");
                String input = scanner.nextLine().trim().toLowerCase();

                if (input.equals("yes")) {
                    Tyre newTyre = IOManager.loadLapData("sample_data/tyre_wear_soft.txt", "Soft"); // Static ref for demo
                    car.changeTyre(newTyre);
                    replaced = true;
                    System.out.println("✅ You boxed for new tyres. Race continues.");
                }
            }

            car.advanceLap();
        }

        System.out.println("🏁 Race ended!");
    }
}