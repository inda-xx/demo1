public class ScoreManager {
    private int score = 0;

    public void addPoints(int points) {
        score += points;
    }

    public int getScore() {
        return score;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);
    }
}