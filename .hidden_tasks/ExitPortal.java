public class ExitPortal extends GameObject {

    public ExitPortal(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {
        // No active update logic
    }

    @Override
    public void render() {
        // Draw portal animation or tile
    }
}

// File: com/bytequest/movement/MovementStrategy.java
package com.bytequest.movement;

import com.bytequest.model.Enemy;

/**
 * Strategy interface for defining how enemies move.
 */
public interface MovementStrategy {
    void move(Enemy enemy);
}