package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LevelLoaderTest {

    @Test
    public void loadValidFileParsesAllValidLines() throws Exception {
        Path temp = Files.createTempFile("level", ".txt");
        Files.writeString(temp, """
                # A comment
                PLAYER 10 15
                bug 1 2
                BUG 2 3 PATROL
                UNKNOWN 5 5
                PLAYER 9
                TOKEN 7.5 8.5
                """);

        LevelLoader loader = new LevelLoader();
        ArrayList<GameObject> objects = loader.load(temp.toAbsolutePath().toString());

        // Expected objects: PLAYER, BUG (CHASE), BUG (PATROL), TOKEN
        assertEquals(4, objects.size());

        // Verify that each expected type exists exactly once
        long players = objects.stream().filter(o -> o instanceof Player).count();
        long chaseBugs = objects.stream().filter(o -> {
            if (!(o instanceof Enemy e)) return false;
            try {
                var f = Enemy.class.getDeclaredField("movement");
                f.setAccessible(true);
                return f.get(e) instanceof DirectChaseStrategy;
            } catch (Exception ex) {
                return false;
            }
        }).count();
        long patrolBugs = objects.stream().filter(o -> {
            if (!(o instanceof Enemy e)) return false;
            try {
                var f = Enemy.class.getDeclaredField("movement");
                f.setAccessible(true);
                return f.get(e) instanceof PatrolStrategy;
            } catch (Exception ex) {
                return false;
            }
        }).count();
        long tokens = objects.stream().filter(o -> o instanceof KnowledgeToken).count();

        assertEquals(1, players);
        assertEquals(1, chaseBugs);
        assertEquals(1, patrolBugs);
        assertEquals(1, tokens);
    }

    @Test
    public void loadHandlesNonExistentFileGracefully() {
        LevelLoader loader = new LevelLoader();
        ArrayList<GameObject> objects = loader.load("definitely/does/not/exist_lol.level");
        assertNotNull(objects);
        assertTrue(objects.isEmpty());
    }
}

// File: EnemyTest.java
import org.junit.Test;

import static org.junit.Assert.*;

