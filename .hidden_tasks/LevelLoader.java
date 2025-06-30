import java.util.List;
import java.util.ArrayList;

public class LevelLoader {

    /**
     * Parses a level file and creates the corresponding game objects.
     * 
     * @param filepath path to the level file
     * @return list of game objects as read from the file
     */
    public ArrayList<GameObject> load(String filepath) {
        ArrayList<GameObject> objects = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // Ignore comments and empty lines
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] tokens = line.split("\\s+");
                if (tokens.length == 0) {
                    continue;
                }

                try {
                    GameObject obj = EntityFactory.create(tokens);
                    if (obj != null) {
                        objects.add(obj);
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Warning [line " + lineNumber + "]: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to load level: " + e.getMessage());
        }

        return objects;
    }
}