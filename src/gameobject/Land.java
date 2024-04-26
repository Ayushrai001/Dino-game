package gameobject;

import javainterface.GameScreen;
import util.Resource;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.BufferedImage;
import java.util.Random;

import static javainterface.GameScreen.GROUNDY;

public class Land {
    public BufferedImage imageLand1 , imageLand2 , imageLand3;
    private List<ImageLand> listImage;
    private Random random;

    public Land(GameScreen game){
        random = new Random();
        imageLand1 = Resource.getResourcerImage("data/land1.png");
        imageLand2 = Resource.getResourcerImage("data/land2.png");
        imageLand3 = Resource.getResourcerImage("data/land3.png");
        listImage = new ArrayList<ImageLand>();
        int length = 600/imageLand1.getWidth()+2;
        for (int i = 0; i < length; i++) {
            ImageLand imageLand = new ImageLand();
            imageLand.posX  = i*imageLand1.getWidth();
            imageLand.image = getLandImage();
            listImage.add(imageLand);
        }

    }

    public void update(){
        for(ImageLand imageLand :listImage){
            imageLand.posX-=3;
        }
        ImageLand firstElement = listImage.get(0);
        if(listImage.get(0).posX + imageLand1.getWidth() < 0){
            firstElement.posX = listImage.get(listImage.size()-1).posX + imageLand1.getWidth();
            listImage.add(firstElement);
            listImage.remove((0));
        }
    }

    public void draw(Graphics g){
        for (ImageLand imageLand:listImage){
            g.drawImage(imageLand.image,imageLand.posX,(int)GROUNDY-20,null);
        }
    }

    private BufferedImage getLandImage(){
        int i = random.nextInt(8);
        switch(i){
            case 0: return imageLand1;
            case 1: return imageLand3;
            default: return imageLand2;
        }
    }

    private class ImageLand{
        int posX;
        BufferedImage image;
    }
}
