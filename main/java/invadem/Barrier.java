package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Barrier extends Sprite{
    PApplet p;
    PImage p2,p3;
    float x,y,a,b;

    public PImage getP2() {
        return p2;
    }

    public PImage getP3() {
        return p3;
    }

    public Barrier(PImage p1, PImage p2, PImage p3, int x, int y, int widthBar, int heightBar) {
        super(p1,x,y,widthBar,heightBar,3);
        this.p2 = p2;
        this.p3 = p3;
    }

    public void draw(PApplet p) {
        if (getHealth()==3){
            p.image(getImg1(),getX(), getY(), getWidth(), getHeight());}
        else if (getHealth()==2) {
            p.image(this.p2,getX(), getY(), getWidth(), getHeight());
        }
        else if (getHealth()==1){
            p.image(this.p3,getX(), getY(), getWidth(), getHeight());
        }


    }
    public static void main(String[] args){}

}