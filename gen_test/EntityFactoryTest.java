package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EntityFactoryTest {

    private String[] playerTokens;
    private String[] bugDefaultTokens;
    private String[] bugPatrolTokens;

    @Before
    public void setUp() {
        playerTokens      = new String[]{"PLAYER", "10", "20"};
        bugDefaultTokens  = new String[]{"BUG", "5.5", "6.6"};
        bugPatrolTokens   = new String[]{"BUG", "1", "2", "PATROL"};
    }

    @Test
    public void createPlayerReturnsPlayerWithCorrectCoordinates() {
        GameObject obj = EntityFactory.create(playerTokens);
        assertTrue(obj instanceof Player);
        assertEquals(10f, obj.getX(), 0.0001f);
        assertEquals(20f, obj.getY(), 0.0001f);
    }

    @Test
    public void createBugWithoutStrategyYieldsDirectChaseStrategy() throws Exception {
        GameObject obj = EntityFactory.create(bugDefaultTokens);
        assertTrue(obj instanceof Enemy);

        Field movementField = Enemy.class.getDeclaredField("movement");
        movementField.setAccessible(true);

        Object strategy = movementField.get(obj);
        assertTrue(strategy instanceof DirectChaseStrategy);
    }

    @Test
    public void createBugWithPatrolStrategyYieldsPatrolStrategy() throws Exception {
        GameObject obj = EntityFactory.create(bugPatrolTokens);
        assertTrue(obj instanceof Enemy);

        Field movementField = Enemy.class.getDeclaredField("movement");
        movementField.setAccessible(true);

        Object strategy = movementField.get(obj);
        assertTrue(strategy instanceof PatrolStrategy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUnknownEntityThrowsException() {
        EntityFactory.create(new String[]{"ALIEN", "1", "2"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithInsufficientParametersThrowsException() {
        EntityFactory.create(new String[]{"PLAYER", "1"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void createWithInvalidFloatThrowsException() {
        EntityFactory.create(new String[]{"PLAYER", "X", "3"});
    }

    @Test
    public void entityTypeParsingIsCaseInsensitive() {
        GameObject obj = EntityFactory.create(new String[]{"player", "3", "4"});
        assertTrue(obj instanceof Player);
        assertEquals(3f, obj.getX(), 0.0001f);
    }
}

// File: LevelLoaderTest.java
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.*;

