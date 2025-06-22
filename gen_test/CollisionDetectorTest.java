package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollisionDetectorTest {

    @Test
    public void testEntitiesAreColliding() {
        GameEntity a = new Item(100, 100);
        GameEntity b = new Item(110, 110);
        assertTrue(CollisionDetector.isColliding(a, b));
    }

    @Test
    public void testEntitiesAreNotColliding() {
        GameEntity a = new Item(0, 0);
        GameEntity b = new Item(100, 100);
        assertFalse(CollisionDetector.isColliding(a, b));
    }

    @Test
    public void testPlayerAndEntityAreColliding() {
        Player p = new Player(100, 100);
        GameEntity e = new Item(110, 110);
        assertTrue(CollisionDetector.isColliding(p, e));
    }

    @Test
    public void testPlayerAndEntityAreNotColliding() {
        Player p = new Player(0, 0);
        GameEntity e = new Item(200, 200);
        assertFalse(CollisionDetector.isColliding(p, e));
    }

    @Test
    public void testCollisionAtBoundaryEdge() {
        GameEntity a = new Item(0, 0);
        GameEntity b = new Item(20, 0); // Touching right edge
        assertTrue(CollisionDetector.isColliding(a, b));
    }
}

// File: ScoreManagerTest.java
import org.junit.Test;

import static org.junit.Assert.*;

