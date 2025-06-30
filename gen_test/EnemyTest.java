package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {

    @Test
    public void updateInvokesMovementStrategy() {
        class SpyStrategy implements MovementStrategy {
            boolean called = false;
            @Override
            public void move(Enemy enemy) {
                called = true;
            }
        }

        SpyStrategy spy = new SpyStrategy();
        Enemy enemy = new Enemy(0, 0, spy);

        enemy.update();
        assertTrue(spy.called);
    }

    @Test
    public void directChaseStrategyMoveExecutesWithoutException() {
        Enemy enemy = new Enemy(0, 0, new DirectChaseStrategy());
        // Should not throw
        enemy.update();
    }

    @Test
    public void patrolStrategyMoveExecutesWithoutException() {
        Enemy enemy = new Enemy(0, 0, new PatrolStrategy());
        // Should not throw
        enemy.update();
    }
}