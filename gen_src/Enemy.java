public class Enemy extends GameEntity {
    private String behaviorType;
    private int speedX;
    private int speedY;

    public Enemy(int x, int y, String behaviorType) {
        super(x, y);
        // initialize fields
    }

    public void update(Player player) {
        // implement enemy behavior based on behaviorType
    }

    @Override
    public void draw(Graphics g) {
        // draw enemy graphics
    }

    @Override
    public Rectangle getBounds() {
        // return bounding box
    }
}