package gameobject;

import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Enemy{
    public BufferedImage image;
    private int posX , posY;
    private Rectangle rect;
    private MainCharacter mainCharacter;
    public boolean isScoreGot = false;

    public Cactus(MainCharacter mainCharacter){
        this.mainCharacter = mainCharacter;
        image = Resource.getResourcerImage("data/cactus1.png");
        posX = 200;
        posY = 95;
        rect = new Rectangle();
//        rect.intersects(rect);  // return true when 2 rect gets collision
    }

    @Override
    public boolean isOutOfScreen() {
        return (posX+image.getWidth()<0);
    }

    @Override
    public boolean isScoreGot(){
            return isScoreGot;
    }

    @Override
    public void setIsScoreGot(boolean isScoreGot){
        this.isScoreGot = isScoreGot;
    }

    public void update(){
        posX-=3;
        rect.x = posX;
        rect.y = posY;
        rect.width = image.getWidth();
        rect.height=image.getHeight();
    }
    @Override
    public Rectangle getBounds(){
        return rect;
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(image , posX,posY,null);
    }

    public void setX(int x){
        posX = x;
    }
    public void setY(int y ){
        posY = y;
    }

    public void setImage(BufferedImage image){
        this.image = image;
    }

    @Override
    public boolean isOver(){
        return (mainCharacter.getX()>posX);
    }


}
