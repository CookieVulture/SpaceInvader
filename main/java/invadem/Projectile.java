package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Projectile extends Sprite {
    private int velocity;
    private boolean isAlive;

    public Projectile(PImage img1, int x, int y, int widthProj, int heightProj, int velocity, int health) {
        super(img1, x, y, widthProj, heightProj, health);            //Constructor takes the PApplet as parameter
        this.velocity = velocity;
        this.isAlive = true;
    }

    public boolean getAlive(){
        return this.isAlive;
    }

    public void dead(){this.isAlive = false;}


    public void direction(){
        this.move(this.velocity);
    }       //move the tank 1 frame for every time draw function is called

    public void draw(PApplet p) {                                       //Sh
        p.image(getImg1(),getX(),getY(), getWidth(),getHeight());
        direction();
    }

    public void move(int i) {
        setY(getY()+i);
    }

    public int getVelocity(){
        return this.velocity;
    }


    public static void main(String[] args){}

}