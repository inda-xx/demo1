public class CollisionDetector {
    public static boolean isColliding(GameEntity a, GameEntity b) {
        return a.getBounds().intersects(b.getBounds());
    }

    public static boolean isColliding(Player p, GameEntity e) {
        return p.getBounds().intersects(e.getBounds());
    }
}

// File: GameState.java
public enum GameState {
    START, PLAYING, GAME_OVER
}