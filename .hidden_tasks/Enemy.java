public class Enemy extends GameObject {

    private final MovementStrategy movement;

    public Enemy(float x, float y, MovementStrategy movement) {
        super(x, y);
        this.movement = movement;
    }

    @Override
    public void update() {
        movement.move(this);
    }

    @Override
    public void render() {
        // Render enemy sprite
    }
}