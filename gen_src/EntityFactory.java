// File: EntityFactory.java

import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;
import com.bytequest.model.GameObject;

public class EntityFactory {

    private static final Map<String, Function<String[], GameObject>> registry = new HashMap<>();

    static {
        // register entity constructors here
    }

    public static GameObject create(String[] tokens) {
        // validate tokens and delegate to appropriate constructor
        return null;
    }

    private static float parseFloat(String token) {
        // convert token to float or throw IllegalArgumentException
        return 0f;
    }
}


// File: com/bytequest/model/GameObject.java

package com.bytequest.model;

public abstract class GameObject {

    protected float x;
    protected float y;

    public GameObject(float x, float y) {
        // initialize position
    }

    public float getX() {
        return 0f;
    }

    public float getY() {
        return 0f;
    }

    public abstract void update();

    public abstract void render();
}