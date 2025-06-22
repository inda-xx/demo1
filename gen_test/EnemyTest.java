package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {

    @Test
    public void testZigZagBehaviorInitialMovement() {
        Enemy enemy = new Enemy(100, 100, "ZIGZAG");
        Player player = new Player(200, 200);

        enemy.update(player);
        assertEquals(102, enemy.getX());
        assertEquals(100, enemy.getY());
    }

    @Test
    public void testZigZagBehaviorDirectionChange() {
        Enemy enemy = new Enemy(771, 100, "ZIGZAG");
        Player player = new Player(200, 200);

        enemy.update(player);
        assertEquals(-2 + 771, enemy.getX());
        assertEquals(130, enemy.getY());
    }

    @Test
    public void testZigZagBehaviorDirectionChangeLeftBoundary() {
        Enemy enemy = new Enemy(-1, 100, "ZIGZAG");
        Player player = new Player(200, 200);

        enemy.update(player);
        assertEquals(1, enemy.getX());
        assertEquals(130, enemy.getY());
    }

    @Test
    public void testFollowBehaviorMovesTowardsPlayerTopLeft() {
        Enemy enemy = new Enemy(150, 150, "FOLLOW");
        Player player = new Player(100, 100);

        enemy.update(player);
        assertEquals(149, enemy.getX());
        assertEquals(149, enemy.getY());
    }

    @Test
    public void testFollowBehaviorMovesTowardsPlayerBottomRight() {
        Enemy enemy = new Enemy(100, 100, "FOLLOW");
        Player player = new Player(150, 150);

        enemy.update(player);
        assertEquals(101, enemy.getX());
        assertEquals(101, enemy.getY());
    }

    @Test
    public void testFollowBehaviorNoMovementIfAlreadyAtPlayerPosition() {
        Enemy enemy = new Enemy(150, 150, "FOLLOW");
        Player player = new Player(150, 150);

        enemy.update(player);
        assertEquals(150, enemy.getX());
        assertEquals(150, enemy.getY());
    }

    @Test
    public void testGetBoundsReturnsCorrectRectangle() {
        Enemy enemy = new Enemy(100, 150, "FOLLOW");
        Rectangle bounds = enemy.getBounds();
        assertEquals(new Rectangle(100, 150, 30, 30), bounds);
    }

    @Test
    public void testConstructorSetsBehaviorCorrectly() {
        Enemy zigzag = new Enemy(100, 100, "ZIGZAG");
        Enemy follow = new Enemy(200, 150, "FOLLOW");
        Player dummy = new Player(0, 0);

        zigzag.update(dummy);
        follow.update(dummy);

        assertEquals(102, zigzag.getX());
        assertEquals(100, zigzag.getY());
        assertEquals(199, follow.getX());
        assertEquals(149, follow.getY());
    }

    @Test
    public void testInvalidBehaviorDoesNothing() {
        Enemy e = new Enemy(100, 100, "INVALID");
        Player p = new Player(200, 200);
        e.update(p);

        assertEquals(100, e.getX());
        assertEquals(100, e.getY());
    }
}

// File: CollisionDetectorTest.java
import org.junit.Test;

import static org.junit.Assert.*;

