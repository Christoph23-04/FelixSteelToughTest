package fsteel.gameclock.entity;

import java.awt.*;
import java.util.ArrayList;

public class HitAbleEntity extends Entity{

    private HitBox hitBox;
    private ArrayList<HitAction> hitActions;
    private boolean areBoundsEqualsToSkin;

    public HitAbleEntity(){
        hitBox = null;
        hitActions = new ArrayList<HitAction>();
        areBoundsEqualsToSkin = true;
    }

    /**
     * @param absXPos x Position of the point you want to test on the screen
     * @param absYPos y Position of the point you want to test on the screen
     * @return true if the given position is in the HitBox of this Entity on the screen
     */
    public boolean isInHitBox(int absXPos, int absYPos){
        return hitBox.contains(absXPos - super.getXPosOnScreen(), absYPos - super.getYPosOnScreen());
    }

    @Override
    public void setSkin(Skin skin){
        super.setSkin(skin);
        if(areBoundsEqualsToSkin){
            hitBox = skin.getHitBox();
        }
    }

    /**
     * Changes the HitBox, if areBoundsEqualsToSkin is set to false
     */
    public void setHitBox(HitBox hitBox){
        if(!areBoundsEqualsToSkin){
            this.hitBox = hitBox;
        }
    }

    public void setBoundsEqualsToSkin(boolean shouldBoundsBeEqualToSkin){
        this.areBoundsEqualsToSkin = shouldBoundsBeEqualToSkin;
    }

    public boolean areBoundsEqualsToSkin(){
        return areBoundsEqualsToSkin;
    }

    protected HitBox getHitBox(){
        return hitBox;
    }

}
