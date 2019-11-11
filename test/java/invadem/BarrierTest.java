package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class BarrierTest {

    @Before
    public void setup() {
        CopyOnWriteArrayList<Barrier> bar = new CopyOnWriteArrayList<>();
    }

    @Test
    public void barrierConstruction() {
        Barrier b = new Barrier(null,null,null,10,10,8,8);
        assertNotNull(b);
        assertEquals(10,b.getX());
        assertEquals(10,b.getY());
        assertEquals(8,b.getWidth());
        assertEquals(8,b.getHeight());
    }

    @Test
    public void testBarrierCollision() {
        Barrier b = new Barrier(null,null,null,10,10,8,3);
        Projectile p = new Projectile(null,10,10,2,1,1,1);
        PApplet pro = new PApplet();
        Game game = new Game (pro);
        pro.delay(3000);
        assertEquals(true,game.isCollision(b,p));
   }

    @Test
    public void testBarrierHitPointsMax() {
        Barrier b = new Barrier(null,null,null,10,10,8,3);
        assertEquals(3, b.getHealth());
        assertEquals(null,b.getImg1());
    }

    @Test
    public void testBarrierHitPointMax1hit() {
        Barrier b = new Barrier(null,null,null,10,10,8,3);
        Projectile p = new Projectile(null,10,10,2,1,1,1);
        b.isHit(p.getHealth());
        assertEquals(2, b.getHealth());
        assertEquals(null,b.getP2());
    }

    @Test
    public void testBarrierHitPointsMax2hit() {
        Barrier b = new Barrier(null,null,null,10,10,8,3);
        Projectile p = new Projectile(null,10,10,2,1,1,1);
        b.isHit(p.getHealth());
        b.isHit(p.getHealth());
        assertEquals(1, b.getHealth());
        assertEquals(null,b.getP3());
    }


    @Test
    public void testBarrierIsDestroyed() {
        Barrier b = new Barrier(null,null,null,10,10,8,3);
        Projectile p = new Projectile(null,10,10,2,1,1,1);
        b.isHit(p.getHealth());
        b.isHit(p.getHealth());
        b.isHit(p.getHealth());
        assertEquals(false, b.isLive());
    }

}
