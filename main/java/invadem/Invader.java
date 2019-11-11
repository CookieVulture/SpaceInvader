package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Invader extends Sprite {
    private int speed,steps, down, health;
    private int frame = 0;
    private boolean isAlive;
    private boolean moveDown;
    PImage img2;

    public Invader(PImage img1,PImage img2, int x, int y, int widthInv, int heightInv, int health) {
        super(img1, x, y, widthInv, heightInv, health);            //Constructor takes the PApplet as parameter
        this.speed = 1;
        this.steps = 0;
        this.isAlive = true;
        this.down = 0;
        this.health = health;
        this.moveDown = false;
        this.img2 = img2;
    }

    public void isDead(){
        this.isAlive = false;
    }

    public boolean isAlive(){
        return this.isAlive;
    }

    public void draw(PApplet p) {
        if (moveDown == false) {
            p.image(getImg1(), getX(), getY(), getWidth(), getHeight());
        }
        else{
            p.image(img2, getX(), getY(), getWidth(), getHeight());
        }
            move();

    }

    public void move() {
        if (frame%2==0) {
            if (this.steps == 30) {
                moveDown = true;
                setY(getY() + 1);
                this.down+=1;
                if (this.down==7) {
                    this.steps = 0;
                    setX(getX()+this.speed);
                    this.down = 0;
                    moveDown = false;
                    this.speed *= -1;
                };
            } else if (getX() > 180 & getX() < 480 & this.steps < 30) {
                setX(getX()+this.speed);
                this.steps += 1;
            }
        }
        frame+=1;
    }


    public static void main(String[] args){}


}