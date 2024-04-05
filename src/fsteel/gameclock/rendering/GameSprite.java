package fsteel.gameclock.rendering;

import fsteel.main.GameSettings;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameSprite extends BufferedImage{

    public GameSprite(){
        super(GameSettings.CONSTANT_FOR_NORMAL_SCREEN_WIDTH, GameSettings.CONSTANT_FOR_NORMAL_SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawBackground();
    }

    public void drawObject(Image img, int x, int y){
        super.getGraphics().drawImage(img, x, y, null);
    }

    public void drawBackground(){
        Graphics g = super.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0,super.getWidth(), super.getHeight());
    }

    public void resetSprite(){
        drawBackground();
    }

    public Image getScaledImage(double widthOffset, double heightOffset){
        return super.getScaledInstance((int)(widthOffset*GameSettings.CONSTANT_FOR_NORMAL_SCREEN_WIDTH),
                (int)(heightOffset*GameSettings.CONSTANT_FOR_NORMAL_SCREEN_HEIGHT), Image.SCALE_DEFAULT);
    }
}
