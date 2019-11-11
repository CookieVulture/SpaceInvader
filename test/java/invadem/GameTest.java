package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.core.PApplet;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class GameTest extends App{
    Game game;
    App p;
    @Before
    public void setup(){
        game = new Game(p);
        PApplet.main("invadem.App");
        delay(4000);


    }

    @Test
    public void scores() {
        assertEquals(10000,game.getHighScore());
        assertEquals(0,game.getTotalScore());
        assertEquals(3,game.getUserLives());
    }

    @Test
    public void images(){
        assertEquals(null,game.getArmouredImage());
        assertEquals(null,game.getInvaderDown());
        assertEquals(null,game.getInvaderImage());
        assertEquals(null,game.getArmouredDown());
        assertEquals(null,game.getPowerDown());
        assertEquals(null,game.getPowerImage());
        assertEquals(null,game.getL1());
        assertEquals(null,game.getL11());
        assertEquals(null,game.getL12());
        assertEquals(null,game.getC1());
        assertEquals(null,game.getC12());
        assertEquals(null,game.getC11());
        assertEquals(null,game.getR1());
        assertEquals(null,game.getR11());
        assertEquals(null,game.getR12());
        assertEquals(null,game.getS2());
        assertEquals(null,game.getS22());
        assertEquals(null,game.getS21());
    }

    @Test
    public void drawBar(){
        CopyOnWriteArrayList<Barrier> bar = new CopyOnWriteArrayList<>();
        game.drawBarrier(this,20,20,8,8,bar);
        assertEquals(7,bar.size());
    }

    @Test
    public void drawInv(){
        CopyOnWriteArrayList<Invader> inv = new CopyOnWriteArrayList<>();
        game.drawInvaders(this,inv);
        assertEquals(40,inv.size());
    }




}
