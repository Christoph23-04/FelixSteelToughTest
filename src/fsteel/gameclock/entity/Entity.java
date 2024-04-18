package fsteel.gameclock.entity;

import fsteel.gameclock.entity.skin.Skin;
import fsteel.main.Game;

import java.awt.*;

public class Entity implements ScreenObject{

    private double xPos;
    private double yPos;
    private Skin skin;

    public Entity(){
        this(0,0);
    }

    public Entity(int xPos, int yPos){
        this(Skin.createBasicSkin(20,20), xPos,yPos );
    }

    public Entity(Skin skin, double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        setSkin(skin);
    }

    public void appear(){
        Game.getRenderer().addScreenObject(this);
    }

    public void disappear(){
        Game.getRenderer().removeScreenObject(this);
    }

    public void setLocation(double xPos, double yPos){
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

    public boolean isOnScreen(){
        return Game.getRenderer().isObjectRendered(this);
    }

}
