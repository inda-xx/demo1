import java.util.Map;
import java.util.HashMap;

public class EntityFactory {

    private static final Map<String, Function<String[], GameObject>> registry = new HashMap<>();

    static {
        // Register known entity types
        registry.put("PLAYER", tokens -> new Player(parseFloat(tokens[1]), parseFloat(tokens[2])));

        registry.put("BUG", tokens -> {
            float x = parseFloat(tokens[1]);
            float y = parseFloat(tokens[2]);
            String strategyName = tokens.length >= 4 ? tokens[3] : "CHASE";
            MovementStrategy strategy = switch (strategyName) {
                case "PATROL" -> new PatrolStrategy();
                default -> new DirectChaseStrategy();
            };
            return new Enemy(x, y, strategy);
        });

        registry.put("WALL", tokens -> new Wall(parseFloat(tokens[1]), parseFloat(tokens[2])));

        registry.put("TOKEN", tokens -> new KnowledgeToken(parseFloat(tokens[1]), parseFloat(tokens[2])));

        registry.put("PORTAL", tokens -> new ExitPortal(parseFloat(tokens[1]), parseFloat(tokens[2])));
    }

    /**
     * Creates a GameObject from token array.
     *
     * @param tokens array of string tokens from level file
     * @return instantiated GameObject
     * @throws IllegalArgumentException if the type or parameters are invalid
     */
    public static GameObject create(String[] tokens) {
        if (tokens.length < 3) {
            throw new IllegalArgumentException("Insufficient parameters: " + String.join(" ", tokens));
        }

        String type = tokens[0].toUpperCase();
        Function<String[], GameObject> constructor = registry.get(type);
        if (constructor == null) {
            throw new IllegalArgumentException("Unknown entity type: " + type);
        }

        return constructor.apply(tokens);
    }

    private static float parseFloat(String token) {
        try {
            return Float.parseFloat(token);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid float value: " + token);
        }
    }
}

// File: com/bytequest/model/GameObject.java
package com.bytequest.model;

/**
 * Base abstract class for all entities present in the game world.
 */
public abstract class GameObject {

    protected float x;
    protected float y;

    public GameObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void update();

    public abstract void render();
}