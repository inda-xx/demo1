public class Player {
    private int x, y;
    private int speed = 5;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(String direction) {
        if ("UP".equals(direction) && y - speed >= 0) y -= speed;
        if ("DOWN".equals(direction) && y + speed <= 570) y += speed;
        if ("LEFT".equals(direction) && x - speed >= 0) x -= speed;
        if ("RIGHT".equals(direction) && x + speed <= 770) x += speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 30, 30);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}