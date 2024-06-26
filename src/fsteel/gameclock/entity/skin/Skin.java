package fsteel.gameclock.entity.skin;

import fsteel.gameclock.entity.ScreenPosition;
import fsteel.gameclock.entity.hitBox.HitBox;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Skin{

    public static Skin createBasicSkin(int xSize, int ySize, Color c){
        BufferedImage img = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(c);
        g.fillRect(0,0,xSize, ySize);
        g.dispose();
        return new Skin(img);
    }

    public static Skin createBasicSkin(int xSize, int ySize){
        return createBasicSkin(xSize, ySize, Color.MAGENTA);
    }

    private final Image appearance;

    public Skin(Image appearance){
        this.appearance = appearance;
    }

    public HitBox getAptHitBox(ScreenPosition sp){
        int skinWidth = appearance.getWidth(null);
        int skinHeight = appearance.getHeight(null);
        return HitBox.createSquareHitbox(skinWidth, skinHeight, sp);
    }

    public Image getAppearance(){
        return appearance;
    }

}
