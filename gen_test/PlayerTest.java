package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testInitialPosition() {
        Player player = new Player(50, 60);
        assertEquals(50, player.getX());
        assertEquals(60, player.getY());
    }

    @Test
    public void testMoveUp() {
        Player player = new Player(100, 100);
        player.move("UP");
        assertEquals(95, player.getY());
    }

    @Test
    public void testMoveDown() {
        Player player = new Player(100, 100);
        player.move("DOWN");
        assertEquals(105, player.getY());
    }

    @Test
    public void testMoveLeft() {
        Player player = new Player(100, 100);
        player.move("LEFT");
        assertEquals(95, player.getX());
    }

    @Test
    public void testMoveRight() {
        Player player = new Player(100, 100);
        player.move("RIGHT");
        assertEquals(105, player.getX());
    }

    @Test
    public void testBoundaryUpperLeft() {
        Player player = new Player(0, 0);
        player.move("UP");
        player.move("LEFT");
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }

    @Test
    public void testBoundaryBottomRight() {
        Player player = new Player(770, 570);
        player.move("DOWN");
        player.move("RIGHT");
        assertEquals(770, player.getX());
        assertEquals(570, player.getY());
    }

    @Test
    public void testGetBounds() {
        Player player = new Player(150, 250);
        Rectangle expected = new Rectangle(150, 250, 30, 30);
        assertEquals(expected, player.getBounds());
    }
}