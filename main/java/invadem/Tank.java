package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Tank extends Sprite {

    public boolean moveLeft = false;
    public boolean moveRight = false;


    public Tank(PImage img1, int x, int y, int widthTank, int heightTank, int health) {
        super(img1, x, y, widthTank, heightTank, health);            //Constructor takes the PApplet as parameter
    }

    public void direction(){
        if (this.moveLeft && getX()>180){this.move(-1);}        //move the tank 1 frame for every time draw function is called
        else if(this.moveRight && getX()<460) {                 //the condition of limited place to move
            this.move(1); }
    }

    public void draw(PApplet p) {                                       //Sh
        p.image(getImg1(),getX(), getY(), getWidth(), getHeight());
        direction();
    }

    public void move(int i) {
        super.setX(getX()+i);
    }

    public static void main(String[] args){}

}