package invadem;
import org.junit.Test;
import org.junit.Before;
import processing.core.PApplet;

import static org.junit.Assert.*;
import static processing.core.PConstants.*;

public class AppTest {
    @Test
    public void testAppRuns() {
        App construct = new App();
        assertNotNull(construct);
    }


    @Test
    public void testSinglePlayerFire() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(1));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = LEFT;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = RIGHT;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 83;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        assertNotNull(AppTest2.lastIns.getGameState());
    }

    @Test
    public void testRenderSinglePlayer() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(1));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.setup();
        AppTest2.lastIns.key = ' ';
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
    }

    @Test
    public void testMenu() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(0));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.setup();
        AppTest2.lastIns.keyCode = 83;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 90;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();

    }

    @Test
    public void testNextlv() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(2));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.setup();
    }
//
    @Test
    public void testGameOv() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(3));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.setup();
    }

    @Test
    public void testStat() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(4));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.setup();
    }

    @Test
    public void testBarr() {
        String[] args = {"AppTest"};
        PApplet.runSketch(args, new AppTest2(1));
        try {
            Thread.sleep(250);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.keyCode = 32;
        AppTest2.lastIns.keyPressed();
        AppTest2.lastIns.keyReleased();
        AppTest2.lastIns.setup();

    }

}
