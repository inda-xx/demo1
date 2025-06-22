public class Item extends GameEntity {
    public Item(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 20, 20);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 20, 20);
    }
}