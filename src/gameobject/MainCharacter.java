package gameobject;

import util.Animation;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

import static javainterface.GameScreen.GRAVITY;
import static javainterface.GameScreen.GROUNDY;

public class MainCharacter {

    private float x =0;
    private float y = 0;
    private float speedY = 0;
    private Animation dinoRun;
    private Rectangle rect;
    private boolean isAlive = true;



    public MainCharacter(){
        dinoRun = new Animation(200);
        dinoRun .addFrames(Resource.getResourcerImage("data/main-character1.png"));
        dinoRun .addFrames(Resource.getResourcerImage("data/main-character2.png"));
        rect = new Rectangle();

    }

    public void update(){
        dinoRun.update();
        //lines for jumping
        if(y>=GROUNDY - dinoRun.getFrame().getHeight()){
            speedY=0;
            y=GROUNDY - dinoRun.getFrame().getHeight();
        }else{
            speedY+=GRAVITY;
            y+=speedY;
        }
        rect.x = (int)x;
        rect.y = (int)y;
        rect.width = dinoRun.getFrame().getWidth();
        rect.height = dinoRun.getFrame().getHeight();
    }

    public Rectangle getBound(){
        return rect;
    }
    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawImage(dinoRun.getFrame(),(int)x,(int)y , null);
    }

    public void jump(){

//        System.out.println(y);
        if(y==97.0){
            speedY = -5    ;  // jump
            y += speedY;
        }

    }

    // Getter and Setters------------------------------------

    public float getSpeedY() {
        return speedY;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public boolean getAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    //Getter and setter ends-----------------------


}
