package javainterface;
import gameobject.*;
import util.Resource;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameScreen extends JPanel implements Runnable , KeyListener {
    public static final int GAME_FIRST_STATE = 0;
    public static final int GAME_PLAY_STATE = 1;
    public static final int GAME_OVER_STATE = 2;

    public static final float GRAVITY = 0.15f;
    public static final float GROUNDY = 140;  // acc to pixcel

    private MainCharacter mainCharacter;
    private Thread thread;
    private Land land;
    private Clouds clouds;
    private EnemyManager enemiesmanager;
    private int gameState = GAME_FIRST_STATE;
    private BufferedImage imageGameOverText;
    private Resource resource;
    private int score;
    private  long gameSpeed = 9;
    int count = 1;

    public GameScreen(){
        thread = new Thread(this);
        mainCharacter = new MainCharacter();
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        land = new Land(this);
        clouds = new Clouds();
        enemiesmanager = new EnemyManager(mainCharacter , this);
        imageGameOverText = Resource.getResourcerImage("data/gameover_text.png");
    }

    public void startGame(){
        thread.start();
    }

    @Override
    public void run(){  // run method of Runnable interface
        while (true){
            try {
                update();
                repaint(); // it will paint method again (rectangle will be drawn again)
                if(score == (count*100) && gameSpeed >2) {
                    gameSpeed -= 1;
                    count++;
                    System.out.println(gameSpeed);
                }
                Thread.sleep(gameSpeed); // number increases slowl y

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        switch (gameState) {
            case GAME_PLAY_STATE:
                mainCharacter.update();
                land.update();
                clouds.update();
                enemiesmanager.update();
                if(!mainCharacter.getAlive()) {
                    gameState = GAME_OVER_STATE;

                }
                break;
        }
    }

    public void setScore(int score ){
        this.score +=score;
    }

    @Override
    public void paint(Graphics g){
//        super.paint(g);  // drawing the background again
        g.setColor(Color.decode("#f7f7f7"));
        g.fillRect(0,0,getWidth(),getHeight());
        switch(gameState){
            case GAME_FIRST_STATE:
                mainCharacter.draw(g);
                break;
            case GAME_PLAY_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesmanager.draw(g);
                g.drawString("Hi "+String.valueOf(score) , 500 , 20);
                break;
            case GAME_OVER_STATE:
                clouds.draw(g);
                land.draw(g);
                mainCharacter.draw(g);
                enemiesmanager.draw(g);
                g.drawImage(imageGameOverText , 200 , 50 , null);
                score=0;
                break;
        }


    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Key Pressed");
    }
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Released");
        switch (e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == GAME_FIRST_STATE) gameState = GAME_PLAY_STATE;
                else if(gameState == GAME_PLAY_STATE){
                    mainCharacter.jump();

                }
                else if (gameState == GAME_OVER_STATE) {
                    resetGame();
                    gameState = GAME_PLAY_STATE;
                    count = 1;
                    gameSpeed=9;
                }


        }
    }

    public void resetGame(){
        mainCharacter.setAlive(true);
        mainCharacter.setX(50);
        mainCharacter.setY(60);
        enemiesmanager.reset();
    }

    public void playAudio(String path){
       try{
           File musicPath = new File(path);
           if(musicPath.exists()){
               AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
               Clip clip = AudioSystem.getClip();
               clip.open(audioInput);
               clip.start();
           }else{
               System.out.println(path+" --- not found !");
           }
       }catch(Exception e){
           e.printStackTrace();
       }
    }
}
