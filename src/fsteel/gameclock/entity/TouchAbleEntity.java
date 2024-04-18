package fsteel.gameclock.entity;

import fsteel.gameclock.entity.hitBox.*;
import fsteel.gameclock.entity.skin.Skin;

import java.util.ArrayList;

public class TouchAbleEntity extends Entity{

    private HitBox hitBox;
    private final ArrayList<Touch> currentTouches;
    private final ArrayList<TouchAction> touchActions;
    private boolean areBoundsEqualsToSkin;


    public TouchAbleEntity() {
        hitBox = null;
        currentTouches = new ArrayList<Touch>();
        touchActions = new ArrayList<TouchAction>();
        areBoundsEqualsToSkin = true;
        if(super.getSkin() != null){
            hitBox = super.getSkin().getAptHitBox(this);
        }
    }

    @Override
    public void appear() {
        super.appear();
        TouchCheckProcess.addTouchAbleEntity(this);
    }

    @Override
    public void disappear() {
        super.disappear();
        TouchCheckProcess.removeTouchAbleEntity(this);
    }

    public void onTouch(Touch touch){
        currentTouches.add(touch);
        for(int i = 0; i < touchActions.size(); i++){
            if(touchActions.get(i).getTouchType() == touch.getTouchType()){
                touchActions.get(i).onTouch(touch);
            }
        }
    }

    public void onTouchEnded(Touch touch){
        currentTouches.remove(touch);
        for(int i = 0; i < touchActions.size(); i++){
            if(touchActions.get(i).getTouchType() == touch.getTouchType()){
                touchActions.get(i).onTouchEnded(touch);
            }
        }
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        if (areBoundsEqualsToSkin) {
            hitBox = skin.getAptHitBox(this);
        }
    }

    public void setHitBox(HitBox hitBox) {
        if (!areBoundsEqualsToSkin) {
            this.hitBox = hitBox;
        }
    }

    public void setBoundsEqualsToSkin(boolean shouldBoundsBeEqualToSkin) {
        this.areBoundsEqualsToSkin = shouldBoundsBeEqualToSkin;
    }

    public boolean areBoundsEqualsToSkin() {
        return areBoundsEqualsToSkin;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void addTouchAction(TouchAction ta){
        touchActions.add(ta);
    }

    public void removeTouchAction(TouchAction ta){
        touchActions.remove(ta);
    }

    public ArrayList<Touch> getCurrentTouches(){
        return currentTouches;
    }
}
