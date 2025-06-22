import java.util.List;

public class Game extends JPanel implements ActionListener, KeyListener {
    private Player player;
    private List<GameEntity> entities;
    private ScoreManager scoreManager;
    private GameStateManager gameStateManager;
    private Timer timer;

    public Game() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        player = new Player(100, 100);
        scoreManager = new ScoreManager();
        gameStateManager = new GameStateManager();

        entities = EntityLoader.loadEntities("entities.txt");

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameStateManager.getState() == GameState.PLAYING) {
            for (GameEntity entity : entities) {
                if (entity instanceof Enemy) {
                    ((Enemy) entity).update(player);
                    if (CollisionDetector.isColliding(player, entity)) {
                        gameStateManager.setState(GameState.GAME_OVER);
                    }
                } else if (entity instanceof Item) {
                    if (CollisionDetector.isColliding(player, entity)) {
                        scoreManager.addPoints(10);
                        entity.setMarkedForRemoval(true);
                    }
                }
            }
            entities.removeIf(GameEntity::isMarkedForRemoval);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameStateManager.getState() == GameState.START) {
            g.setColor(Color.WHITE);
            g.drawString("Press SPACE to Start", 350, 300);
        } else if (gameStateManager.getState() == GameState.PLAYING) {
            player.draw(g);
            for (GameEntity entity : entities) {
                entity.draw(g);
            }
            scoreManager.draw(g);
        } else if (gameStateManager.getState() == GameState.GAME_OVER) {
            g.setColor(Color.RED);
            g.drawString("GAME OVER! Score: " + scoreManager.getScore(), 340, 300);
            g.drawString("Press R to Restart", 350, 330);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameStateManager.getState() == GameState.START && e.getKeyCode() == KeyEvent.VK_SPACE) {
            gameStateManager.setState(GameState.PLAYING);
        } else if (gameStateManager.getState() == GameState.GAME_OVER && e.getKeyCode() == KeyEvent.VK_R) {
            player = new Player(100, 100);
            scoreManager = new ScoreManager();
            gameStateManager.setState(GameState.PLAYING);
            entities = EntityLoader.loadEntities("entities.txt");
        }

        if (gameStateManager.getState() == GameState.PLAYING) {
            if (e.getKeyCode() == KeyEvent.VK_UP) player.move("UP");
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) player.move("DOWN");
            else if (e.getKeyCode() == KeyEvent.VK_LEFT) player.move("LEFT");
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT) player.move("RIGHT");
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Arcade Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Game());
        frame.pack();
        frame.setVisible(true);
    }
}

// File: GameEntity.java
import java.awt.*;

public abstract class GameEntity {
    protected int x, y;
    protected boolean remove = false;

    public GameEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics g);
    public abstract Rectangle getBounds();

    public int getX() { return x; }
    public int getY() { return y; }

    public void setMarkedForRemoval(boolean remove) { this.remove = remove; }
    public boolean isMarkedForRemoval() { return remove; }
}