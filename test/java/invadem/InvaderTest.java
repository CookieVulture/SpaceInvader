package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;


public class InvaderTest {

    CopyOnWriteArrayList<Barrier> bar = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Projectile> proj = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<Invader> invaders = new CopyOnWriteArrayList<>();
    PApplet pro = new PApplet();
    Game game = new Game (pro);

    @Before
    public void setup() {
        proj.add(new Projectile(null,10,10,2,1,1,1));
    }


    @Test
    public void testInvaderConstruction() {
        Invader inv = new Invader(null,null,10,10,16,16,1);
        assertNotNull(inv);
    }

    @Test
    public void testInvaderIsNotDead() {
        Invader inv = new Invader(null,null,10,10,16,16,1);
        assertEquals(true,inv.isAlive());
    }

    @Test
    public void testInvaderIsDead() {
        Invader inv = new Invader(null,null,10,10,16,16,1);
        inv.isDead();
        assertEquals(false,inv.isAlive());
    }

    @Test
    public void testInvaderIntersectWithPlayerProjectile() {
        Invader inv = new Invader(null,null,10,10,16,16,1);
        Projectile p = new Projectile(null,10,10,2,1,1,1);
        assertTrue(game.isCollision(p,inv));
    }

    @Test
    public void testInvaderProjectile(){
        Invader inv = new Invader(null,null,10,10,16,16,1);
        invaders.add(inv);
        assertFalse(game.isInvDead(pro,inv,proj,invaders));
    }

    @Test
    public void testPlayerProjectile(){
        Invader inv = new Invader(null,null,10,10,16,16,1);
        proj.clear();
        proj.add(new Projectile(null,10,10,2,1,-1,1));
        invaders.add(inv);
        assertTrue(game.isInvDead(pro,inv,proj,invaders));
    }

    @Test
    public void testProjectileSize(){
        Invader inv = new Invader(null,null,10,10,16,16,1);
        proj.clear();
        proj.add(new Projectile(null,10,10,2,1,-1,1));
        invaders.add(inv);
        game.isInvDead(pro,inv,proj,invaders);
        assertEquals(0,proj.size());
    }





}
