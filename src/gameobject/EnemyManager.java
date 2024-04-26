package gameobject;

import javainterface.GameScreen;
import util.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private List<Enemy> enemies;
    private Random random;
    private BufferedImage imageCactus1 , imageCactus2;
    private MainCharacter mainCharacter;
    private GameScreen gameScreen;


    public EnemyManager(MainCharacter mainCharacter ,GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.mainCharacter = mainCharacter;
        enemies = new ArrayList<Enemy>();
        random = new Random();

        imageCactus1 = Resource.getResourcerImage("data/cactus1.png");
        imageCactus2 = Resource.getResourcerImage("data/cactus2.png");


        Cactus cactus = new Cactus(mainCharacter);
        enemies.add(getRandomCactus());
    }

    public void update(){
        for(Enemy e:enemies){
            e.update();
            if(e.isOver() && !e.isScoreGot()){
                gameScreen.setScore(20);
                e.setIsScoreGot(true);
            }
            if(e.getBounds().intersects(mainCharacter.getBound())){
                mainCharacter.setAlive(false);
            }
        }
        Enemy firstEnemy = enemies.get(0);
        if(enemies.get(0).isOutOfScreen()){
            enemies.remove(firstEnemy);
            enemies.add(getRandomCactus());
        }
    }



    public void draw(Graphics g){
        for(Enemy e: enemies){
            e.draw(g);
        }
    }

    public Cactus getRandomCactus(){
        Cactus cactus;
        cactus = new Cactus(mainCharacter);
        cactus.setX(600);
        if(random.nextBoolean()){
            cactus.setImage(imageCactus1);
        }else {
            cactus.setImage(imageCactus2);
        }
        return cactus;
    }

    public void reset(){
        enemies.clear();
        enemies.add(getRandomCactus());
    }






}
