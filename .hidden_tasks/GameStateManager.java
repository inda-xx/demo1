public class GameStateManager {
    private GameState currentState;

    public GameStateManager() {
        currentState = GameState.START;
    }

    public GameState getState() {
        return currentState;
    }

    public void setState(GameState newState) {
        currentState = newState;
    }
}