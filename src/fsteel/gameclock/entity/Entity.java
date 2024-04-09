package fsteel.gameclock.entity;

import fsteel.gameclock.entity.skin.Skin;
import fsteel.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity implements ScreenObject{

    private double xPos;
    private double yPos;
    private Skin skin;

    public Entity(){
        xPos = 0;
        yPos = 0;
        setSkin(Skin.createBasicSkin(20,20));
    }

    public Entity(Skin skin){
        setSkin(skin);
    }

    public void appear(){
        Game.getRenderer().addScreenObject(this);
    }

    public void disappear(){
        Game.getRenderer().removeScreenObject(this);
    }

    public void setPos(double xPos, double yPos){
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

    public double getCorrectXPos(){
        return xPos;
    }

    @Override
    public int getYPosOnScreen() {
        return (int) yPos;
    }

    public double getCorrectYPos(){
        return yPos;
    }

    @Override
    public Image getAppearance(){
        return skin.getAppearance();
    }

    public Skin getSkin() {
        return skin;
    }

    public int getSimpleXSise(){
        return skin.getAppearance().getWidth(null);
    }

    public int getSimpleYSise(){
        return skin.getAppearance().getHeight(null);
    }

    public boolean isOnScreen(){
        return Game.getRenderer().isObjectRendered(this);
    }

}
