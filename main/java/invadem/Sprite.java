package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class Sprite {
    PImage img1;
    private int x,y,width,height, health;
    private boolean isLive;

    public Sprite(PImage img1, int x, int y, int width, int height, int health) {            //Constructor takes the PApplet as parameter
        this.img1 = img1;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = health;

    }

    public int getY(){
        return (int) this.y;        //Return y-axis position of tank
    }

    public int getX(){
        return (int) this.x;        //Return x-axis position of tank
    }

    public PImage getImg1(){
        return this.img1;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getHealth(){
        return this.health;
    }

    public void setX(int x1){
        this.x = x1;
    }

    public void setY(int y1){
        this.y = y1;
    }

    public void isHit(int damage) {this.health -= damage;}

    public boolean isLive(){
        if (this.health>0){return true;}
        else{return false;}

    }

    public abstract void draw(PApplet p);



    public static void main(String[] args){}


}