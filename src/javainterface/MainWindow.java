package javainterface;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainWindow extends JFrame {
    private GameScreen gameScreen; // GameScreen object
    private static final int screenWidth = 600;

    public MainWindow(){
        super("Dino Game"); // Constructor of JFrame method
        setSize(screenWidth,190);
        setLocation(500 , 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameScreen = new GameScreen();
        add(gameScreen);
        addKeyListener(gameScreen); // adding key listener
    }

    public void paint(Graphics g ){
        super.paint(g); // paint method of the super class // paint a background
        BufferedImage image = null;  // importing image
        try {
            image = ImageIO.read(new File("data/cactus1.png"));
            g.drawImage(image , 100 ,100 ,null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        g.drawLine(10,10,100,100);
    }

    public void startGame(){
        gameScreen.startGame();
    }



    public static void main(String[] args)  {
        MainWindow gw ;
        gw = new MainWindow();
        gw.setVisible(true);
        gw.startGame();
        System.out.println("Hello Deepak");
        Scanner scan = new Scanner(System.in);



    }

}
