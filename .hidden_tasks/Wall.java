public class Wall extends GameObject {

    public Wall(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {
        // Static walls don't update
    }

    @Override
    public void render() {
        // Render wall
    }
}