package fsteel.gameclock.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Skin{

    public static Skin createBasicSkin(int xSize, int ySize){
        BufferedImage img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.MAGENTA);
        g.fillRect(0,0,xSize, ySize);
        g.dispose();
        return new Skin(img);
    }

    private Image appearance;

    public Skin(Image appearance){
        this.appearance = appearance;
    }

    public HitBox getHitBox(){
        int skinWidth = appearance.getWidth(null);
        int skinHeight = appearance.getHeight(null);
        return HitBox.createSquareHitbox(skinWidth, skinHeight);
    }

    public Image getAppearance(){
        return appearance;
    }

}
