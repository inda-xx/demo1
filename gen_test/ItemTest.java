package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {

    @Test
    public void testItemGetBounds() {
        Item item = new Item(100, 200);
        Rectangle expected = new Rectangle(100, 200, 20, 20);
        assertEquals(expected, item.getBounds());
    }

    @Test
    public void testItemMarkedForRemoval() {
        Item item = new Item(50, 70);
        assertFalse(item.isMarkedForRemoval());
        item.setMarkedForRemoval(true);
        assertTrue(item.isMarkedForRemoval());
        item.setMarkedForRemoval(false);
        assertFalse(item.isMarkedForRemoval());
    }
}

// File: PlayerTest.java
import org.junit.Test;

import java.awt.Rectangle;

import static org.junit.Assert.*;

