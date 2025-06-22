public class Enemy extends GameEntity {
    private String behaviorType;
    private int speedX = 2;
    private int speedY = 1;

    public Enemy(int x, int y, String behaviorType) {
        super(x, y);
        this.behaviorType = behaviorType;
    }

    public void update(Player player) {
        if ("ZIGZAG".equals(behaviorType)) {
            x += speedX;
            if (x < 0 || x > 770) {
                speedX = -speedX;
                y += 30;
            }
        } else if ("FOLLOW".equals(behaviorType)) {
            if (player.getX() > x) x += 1;
            else if (player.getX() < x) x -= 1;
            if (player.getY() > y) y += 1;
            else if (player.getY() < y) y -= 1;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 30);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }
}