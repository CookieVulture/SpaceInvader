package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.concurrent.CopyOnWriteArrayList;

public class Game {
    PApplet p;
    private PImage invaderImage, armouredImage, powerImage, invaderDown, armouredDown, powerDown, destroyed;
    private PImage l1,c1,r1,s2, l11,c11,r11,s21,l12,c12,r12,s22;
    private int totalScore=0, highScore=10000, userLives=3;
    private int accuracy=0, attempts=0, death=0, lev=0, totalInvKill=0, highestInvKill=0, bullets=0, tempInvKill=0, highLev=0;
    Game(PApplet p){
        this.p = p;
    }

    public int getAccuracy() {
        if (totalInvKill>0){
            accuracy = bullets/totalInvKill;
        }
        else{
            accuracy = 0;
        }
        return accuracy;
    }

    public int getHighLev(){
        if (lev>highLev){
            highLev = lev;
        }
        return highLev;
    }

    public int getAttempts() {
        return attempts;
    }


    public void setAttempts(int attempts) {
        this.attempts += attempts;
    }

    public void setDeath(int death) {
        this.death += death;
    }

    public void setLev(int lev) {
        this.lev += lev;
    }


    public void setBullets(int bullets) {
        this.bullets += bullets;
    }

    public int getDeath() {
        return death;
    }

    public int getLev() {
        return lev;
    }

    public int getTotalInvKill() {
        return totalInvKill;
    }

    public int getHighestInvKill() {
        if (tempInvKill>highestInvKill){
            highestInvKill = tempInvKill;
        }
        return highestInvKill;
    }

    public void loadImages(PApplet p){
        invaderImage = p.loadImage("invader1.png");
        armouredImage = p.loadImage("invader1_armoured.png");
        powerImage = p.loadImage("invader1_power.png");
        invaderDown = p.loadImage("invader2.png");
        armouredDown = p.loadImage("invader2_armoured.png");
        powerDown = p.loadImage("invader2_power.png");
        l1 = p.loadImage("barrier_left1.png");
        c1 = p.loadImage("barrier_top1.png");
        r1 = p.loadImage("barrier_right1.png");
        s2 = p.loadImage("barrier_solid1.png");
        l11 = p.loadImage("barrier_left2.png");
        c11 = p.loadImage("barrier_top2.png");
        r11 = p.loadImage("barrier_right2.png");
        s21 = p.loadImage("barrier_solid2.png");
        l12 = p.loadImage("barrier_left3.png");
        c12 = p.loadImage("barrier_top3.png");
        r12 = p.loadImage("barrier_right3.png");
        s22 = p.loadImage("barrier_solid3.png");
        destroyed = p.loadImage("destroyed.png");
    }

    public PImage getDestroyed(){return destroyed;}

    public PImage getInvaderImage() {
        return invaderImage;
    }

    public PImage getArmouredImage() {
        return armouredImage;
    }

    public PImage getPowerImage() {
        return powerImage;
    }

    public PImage getInvaderDown() {
        return invaderDown;
    }

    public PImage getArmouredDown() {
        return armouredDown;
    }

    public PImage getPowerDown() {
        return powerDown;
    }

    public PImage getL1() {
        return l1;
    }

    public PImage getC1() {
        return c1;
    }

    public PImage getR1() {
        return r1;
    }

    public PImage getS2() {
        return s2;
    }

    public int getUserLives() {
        return userLives;
    }

    public PImage getL11() {
        return l11;
    }

    public PImage getC11() {
        return c11;
    }

    public PImage getR11() {
        return r11;
    }

    public PImage getS21() {
        return s21;
    }

    public PImage getL12() {
        return l12;
    }

    public PImage getC12() {
        return c12;
    }

    public PImage getR12() {
        return r12;
    }

    public PImage getS22() {
        return s22;
    }

    public int getTotalScore(){return totalScore;}

    public int getHighScore(){return highScore;}

    public void resetStat(){
        this.totalScore = 0;
        this.lev = 0;
        this.tempInvKill = 0;
    }


    protected void drawBarrier(PApplet p, int x, int y, int a, int b, CopyOnWriteArrayList<Barrier> barrierList){
        barrierList.add(new Barrier(getL1(),getL11(),getL12(),x, y, a, b));
        barrierList.add(new Barrier(getC1(),getC11(),getC12(),x+8, y, a, b));
        barrierList.add(new Barrier(getR1(),getR11(),getR12(),x+16, y, a, b));
        barrierList.add(new Barrier(getS2(),getS21(),getS22(),x, y+8, a, b));
        barrierList.add(new Barrier(getS2(),getS21(),getS22(),x+16, y+8, a, b));
        barrierList.add(new Barrier(getS2(),getS21(),getS22(),x , y+16, a , b));
        barrierList.add(new Barrier(getS2(),getS21(),getS22(),x+16, y+16, a, b));
    };
    protected void drawInvaders(PApplet p, CopyOnWriteArrayList<Invader> invadersList) {
        int a = 181, b = 20, n = 0, total = 0, col = 0;
        while (a < 460 & a > 180) {
            if (total == 40) {
                break;
            }
            if (n == 10) {
                n = 0;
                b += 24;
                a = 181;
                col += 1;
            }
            if (col > 1) {
                invadersList.add(new Invader(getInvaderImage(), getInvaderDown(), a, b, 16, 16, 1));
            } else if (col == 1) {
                invadersList.add(new Invader(getPowerImage(), getPowerDown(), a, b, 16, 16, 1));
            } else if (col == 0) {
                invadersList.add(new Invader(getArmouredImage(), getArmouredDown(), a, b, 16, 16, 3));
            }
            a += 26;
            n += 1;
            total += 1;
        }}
    protected boolean isCollision(Sprite r1, Sprite r2) {
        if ((r1.getX() < (r2.getX() + r2.getWidth())) & ((r1.getX() + r1.getWidth()) > r2.getX()) & (r1.getY() < (r2.getY() + r2.getHeight())) & ((r1.getHeight() + r1.getY()) > r2.getY())) {
            return true;
        } else {
            return false;
        }
    }
    protected boolean isInvDead(PApplet p, Invader invader, CopyOnWriteArrayList<Projectile> projectileList, CopyOnWriteArrayList<Invader> invadersList) {
        for (Projectile projectile : projectileList) {
            if ((isCollision(projectile, invader)) & projectile.getVelocity() == -1) {
                invader.isHit(projectile.getHealth());
                if (invader.getHealth() < 1) {
                    invadersList.remove(invader);
                    Music.EXPLODE.play();
                    tempInvKill+=1;
                    totalInvKill += 1;
                    if (invader.getImg1()==getInvaderImage()||invader.getImg1()==getInvaderDown()){
                        totalScore+=100;
                    }
                    else{
                        totalScore+=250;
                    }
                    if (totalScore>highScore){
                        highScore = totalScore;
                    }
                }
                projectileList.remove(projectile);
                return true;
            }
        }
        return false;
    }
    protected boolean isBarDead(PApplet p, Barrier bar, CopyOnWriteArrayList<Projectile> projectileList, CopyOnWriteArrayList<Barrier> barrierList){
        for (Projectile projectile: projectileList){
            if(isCollision(projectile, bar)){
                bar.isHit(projectile.getHealth());
                if (bar.getHealth()<1){barrierList.remove(bar);}
                projectileList.remove(projectile);
                return true;
            }
        }
        return false;
    }



}
