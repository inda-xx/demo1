package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameStateManagerTest {

    @Test
    public void testDefaultStateIsStart() {
        GameStateManager gsm = new GameStateManager();
        assertEquals(GameState.START, gsm.getState());
    }

    @Test
    public void testCanTransitionToPlaying() {
        GameStateManager gsm = new GameStateManager();
        gsm.setState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gsm.getState());
    }

    @Test
    public void testCanTransitionToGameOver() {
        GameStateManager gsm = new GameStateManager();
        gsm.setState(GameState.GAME_OVER);
        assertEquals(GameState.GAME_OVER, gsm.getState());
    }

    @Test
    public void testMultipleTransitions() {
        GameStateManager gsm = new GameStateManager();
        gsm.setState(GameState.PLAYING);
        assertEquals(GameState.PLAYING, gsm.getState());
        gsm.setState(GameState.GAME_OVER);
        assertEquals(GameState.GAME_OVER, gsm.getState());
        gsm.setState(GameState.START);
        assertEquals(GameState.START, gsm.getState());
    }
}

// File: ItemTest.java
import org.junit.Test;

import java.awt.Rectangle;

import static org.junit.Assert.*;

