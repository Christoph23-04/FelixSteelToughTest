package fsteel.gameclock.rendering;

import fsteel.gameclock.entity.ScreenObject;
import fsteel.main.Game;
import fsteel.main.GameSettings;

import java.awt.*;
import java.util.ArrayList;

public class ScreenObjectRenderer implements GraphicRenderer{
    private double currentWidthOffset;
    private double currentHeightOffset;
    private GameSprite sprite;

    private ArrayList<ScreenObject> screenObjects;

    public ScreenObjectRenderer(){
        screenObjects = new ArrayList<ScreenObject>();
        currentWidthOffset = 1;
        currentHeightOffset = 1;
        sprite = new GameSprite();
    }

    @Override
    public void renderObject(Graphics2D g) {
        sprite.resetSprite();
        currentWidthOffset = (double)Game.getBackground().getWidth()/(double)GameSettings.CONSTANT_FOR_NORMAL_SCREEN_WIDTH;
        currentHeightOffset = (double)Game.getBackground().getHeight()/(double)GameSettings.CONSTANT_FOR_NORMAL_SCREEN_HEIGHT;
        for(ScreenObject so : screenObjects){
            if(so != null && so.getAppearance() != null){
                sprite.drawObject(so.getAppearance(), so.getXPosOnScreen(), so.getYPosOnScreen());
                g.drawImage(sprite.getScaledImage(currentWidthOffset, currentHeightOffset), 0,0, null);
            }
        }
    }

    public void addScreenObject(ScreenObject so){
        screenObjects.add(so);
    }

    public void removeScreenObject(ScreenObject so){
        screenObjects.remove(so);
    }

    public boolean isObjectRendered(ScreenObject so){
        return screenObjects.contains(so);
    }
}
