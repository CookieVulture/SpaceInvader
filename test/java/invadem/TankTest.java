package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class TankTest {
    PApplet pro = new PApplet();
    Game game = new Game(pro);
    CopyOnWriteArrayList<Projectile> proj = new CopyOnWriteArrayList<>();


    @Test
    public void testTankConstruction() {
        Tank tank = new Tank(null, 0, 0, 10, 10, 3);
        assertNotNull(tank);
    }

    @Test
    public void testTankIsNotDead() {
        Tank tank = new Tank(null, 0, 0, 10, 10, 3);
        assertEquals(true, tank.isLive());
    }

    @Test
    public void testTankmove() {
        Tank tank = new Tank(null, 200, 0, 10, 10, 3);
        tank.move(1);
        assertEquals(201, tank.getX());
        tank.move(-1);
        assertEquals(200, tank.getX());
        tank.move(461);
        tank.moveLeft = true;
        tank.moveRight = true;
        tank.direction();

    }

    @Test
    public void testTankDestroyed() {
        Tank tank = new Tank(null, 10, 10, 1, 1, 3);
        Projectile p = new Projectile(null, 10, 10, 2, 1, -1, 3);
        assertTrue(game.isCollision(p, tank));
        proj.add(p);
        tank.isHit(p.getHealth());
        assertEquals(0,tank.getHealth());
    }

}
