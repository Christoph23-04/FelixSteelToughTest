package fsteel.gameclock.entity;

import fsteel.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity implements ScreenObject{

    private float xPos;
    private float yPos;
    private Skin skin;

    public Entity(){
        xPos = 0;
        yPos = 0;
        setSkin(Skin.createBasicSkin(20,20));
    }

    public Entity(Skin skin){
        setSkin(skin);
    }

    private BufferedImage createBasicSkin(){
        BufferedImage img = new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0,0,20,20);
        return img;
    }

    public void appear(){
        Game.getRenderer().addScreenObject(this);
    }

    public void disappear(){
        Game.getRenderer().removeScreenObject(this);
    }

    public void setPos(float xPos, float yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void setSkin(Skin skin){
        this.skin = skin;
    }

    @Override
    public int getXPosOnScreen() {
        return (int) xPos;
    }

    public float getCorrectXPos(){
        return xPos;
    }

    @Override
    public int getYPosOnScreen() {
        return (int) yPos;
    }

    public float getCorrectYPos(){
        return yPos;
    }

    @Override
    public Image getAppearance(){
        return skin.getAppearance();
    }

    public Skin getSkin() {
        return skin;
    }

    public boolean isOnScreen(){
        return Game.getRenderer().isObjectRendered(this);
    }

}
