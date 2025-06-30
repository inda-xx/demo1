// File: ExitPortal.java
public class ExitPortal extends GameObject {

    public ExitPortal(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
    }
}

// File: com/bytequest/movement/MovementStrategy.java
package com.bytequest.movement;

import com.bytequest.model.Enemy;

public interface MovementStrategy {
    void move(Enemy enemy);
}