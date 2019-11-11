package invadem;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class App extends PApplet {
    private int gameState, titleScreen = 0, nextLevel = 2, gameOver = 3, gamePlay = 1, stats = 4;
    private boolean shootNow, oneTime, invShoot, titleMusic=true;
    private Tank tank;
    private PImage over, next;
    private PImage projectileImage, projectileLargeImage;
    private List<Barrier> barrierList;
    private List<Invader> invadersList;
    private List<Projectile> projectileList;
    private int shootingTime, invNumber;
    private int counter;
    private Game game;

    public App() {
        projectileList = new CopyOnWriteArrayList<>();
        gameState = 0;
        shootNow = false;
        oneTime = true;
        invadersList = new CopyOnWriteArrayList<>();
        barrierList = new CopyOnWriteArrayList<>();
        invShoot = true;
        shootingTime = 5;
        counter = 0;
        game = new Game(this);

        //Set up your objects
    }

    public void setup() {
        frameRate(60);
        tank = new Tank(loadImage("tank1.png"), width / 2 - 11, height - 40, 22, 14, 3);
        projectileImage = loadImage("projectile.png");
        game.loadImages(this);
        PFont font = createFont("reg_font", 12);
        over = loadImage("gameover.png");
        next = loadImage("nextlevel.png");
        projectileLargeImage = loadImage("projectile_lg.png");

        game.drawBarrier(this, tank.getX(), tank.getY() - 36, 8, 8, (CopyOnWriteArrayList<Barrier>) barrierList);
        game.drawBarrier(this, 200, tank.getY() - 36, 8, 8, (CopyOnWriteArrayList<Barrier>) barrierList);
        game.drawBarrier(this, 430, tank.getY() - 36, 8, 8, (CopyOnWriteArrayList<Barrier>) barrierList);

        textFont(font);
        game.drawInvaders(this, (CopyOnWriteArrayList<Invader>) invadersList);

    }

    public void settings() {
        size(640, 480);

    }

    public void draw() {
        invNumber = invadersList.size();
        if (gameState == 0) {
            if (titleMusic){
                Music.SPACE.play();
                titleMusic = false;
            }
            title();
        } else if (gameState == 1) {
            if (invNumber == 0) {
                gameState = nextLevel;
                counter = 0;
            } else {
                play();
            }
        } else if (gameState == 2) {
            nextLv();
        } else if (gameState == 3) {
            gameOv();
            titleMusic = true;
        } else if (gameState == 4){
            stat();
        }
        counter += 1;
        //Main Game Loop
    }

    private void title() {
        background(0);
        textSize(22);
        text("Welcome to Space Invaders", width / 2 - 120, height / 2 - 102);
        fill(250,0,0);
        text("\n" + "Press Z to start the game", width / 2 - 120, height / 2-82);
        text("\n"+"Press S to check Stats",width/2-120,height/2-52);
        textSize(15);
        text("\n"+"\n"+"Instruction",width/2-120,height/2-10);
        text("\n"+"Press Space to shoot",width/2-120,height/2+40);

    }

    private void gameOv() {
        projectileList.clear();
        invadersList.clear();
        barrierList.clear();
        background(0);
        if (counter < 120) {
            image(over, width / 2, height / 2);
        } else {
            setup();
            gameState = titleScreen;
            counter = 0;
            game.setDeath(1);
            game.setAttempts(1);
        }
    }

    private void nextLv() {
        projectileList.clear();
        barrierList.clear();
        background(0);
        if (counter < 120) {
            image(next, width / 2, height / 2);
        } else {
            setup();
            if (shootingTime>1){
            shootingTime -= 1;}
            else{
                shootingTime = 1;
            }
            gameState = gamePlay;
            game.setLev(1);
        }
    }

    private void stat(){
        background(0);
        textSize(14);
        fill(50,224,11);
        textAlign(CENTER);
        text("Total Attempts: " + str(game.getAttempts()),width/2,50);
        text(" Average Accuracy: " + str(game.getAccuracy()),width/2,80);
        text("Highest Score: " + str(game.getHighScore()),width/2,110);
        text("Total Deaths: " + str(game.getDeath()),width/2,140);
        text("Highest Level: " + str(game.getHighLev()),width/2,170);
        text("Total Invaders Kill: " + str(game.getTotalInvKill()),width/2,200);
        text("Highest Invaders Kill: " + str(game.getHighestInvKill()),width/2,230);
        text("Press Z to start game",width/2,320);
        game.resetStat();
    }

    private void play() {
        background(0);
        textSize(12);
        text("High score:"  + game.getHighScore(), 500, 30);
        text("Score:" + game.getTotalScore(), 30, 30);
        text("Health:" + tank.getHealth(), 30,60);
        text("Remaining Enemies: "+ invadersList.size(), 500,60);
        tank.draw(this);
        tankShoot();
        for (Invader invader : invadersList) {
            if (!game.isInvDead(this, invader, (CopyOnWriteArrayList<Projectile>) projectileList, (CopyOnWriteArrayList<Invader>) invadersList)) {
                invader.draw(this);
                if (invader.getY()==tank.getY()-56){
                    gameState = gameOver;
                    counter = 0;
                }
            }
        }
        for (Projectile projectile : projectileList) {
            if (projectile.getY() > 480 || projectile.getY() < 0) {
                projectileList.remove(projectile);
            }
            projectile.draw(this);
        }
        if ((second() % shootingTime) == 0 & invShoot) {
            invaderShoot();
            invShoot = false;
        }
        if (((second() - 1) % shootingTime) == 0) {
            invShoot = true;
        }
        for (Barrier barrier : barrierList) {
            if (!game.isBarDead(this, barrier, (CopyOnWriteArrayList<Projectile>) projectileList, (CopyOnWriteArrayList<Barrier>) barrierList)) {
                barrier.draw(this);
            }
        }
        for (Projectile proj : projectileList) {
            if (proj.getVelocity() == 1) {
                if (game.isCollision(tank, proj)) {
                    projectileList.remove(proj);
                    tank.isHit(proj.getHealth());
                    Music.EXPLODE.play();
                }
            }
        }
        if (tank.getHealth() < 1) {
            gameState = gameOver;
            counter = 0;
        }
    }

    public void keyPressed() {
        if (keyCode == LEFT) {
            tank.moveLeft = true;
        } else if (keyCode == RIGHT) {
            tank.moveRight = true;
        } else if (key == ' ') {
            shootNow = true;
        } else if (key == 'Z' || key == 'z') {
            gameState = gamePlay;
        } else if (key=='S'|| key =='s'){
            gameState = stats;
        }
    }

    public List<Projectile> getProjectileList() {
        return projectileList;
    }

    public void keyReleased() {
        if (keyCode == LEFT) {
            tank.moveLeft = false;
        } else if (keyCode == RIGHT) {
            tank.moveRight = false;
        } else if (key == ' ') {
            shootNow = false;
            oneTime = true;
        }
    }

    private void tankShoot() {
        if (shootNow & oneTime) {
            projectileList.add(new Projectile(projectileImage, tank.getX() + 11, tank.getY(), 1, 3, -1, 1));
            oneTime = false;
            Music.SHOOT.play();
            game.setBullets(1);
        }
    }

    public void setGameState(int i){
        gameState = i;
    }

    public int getGameState(){
        return gameState;
    }

    private void invaderShoot() {
        if (gameState == gamePlay & invadersList.size() > 0) {
            int num = ThreadLocalRandom.current().nextInt(1, invadersList.size() + 1);
            if (invadersList.get(num - 1).getImg1() == game.getPowerImage() || invadersList.get(num - 1).getImg1() == game.getPowerDown()) {
                projectileList.add(new Projectile(projectileLargeImage, invadersList.get(num - 1).getX(), invadersList.get(num - 1).getY(), 2, 5, 1, 3));
            } else {
                projectileList.add(new Projectile(projectileImage, invadersList.get(num - 1).getX(), invadersList.get(num - 1).getY(), 1, 3, 1, 1));
            }
        }
        Music.SHOOT.play();
    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }


}
