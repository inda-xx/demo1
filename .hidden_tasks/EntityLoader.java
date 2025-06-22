import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class EntityLoader {
    public static List<GameEntity> loadEntities(String filename) {
        List<GameEntity> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ");
                if (parts.length < 4) continue;
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                String subtype = parts[3];

                if ("ENEMY".equals(type)) {
                    list.add(new Enemy(x, y, subtype));
                } else if ("ITEM".equals(type)) {
                    list.add(new Item(x, y));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading entities: " + e.getMessage());
        }
        return list;
    }
}