package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.Vector2D;

public abstract class HitAction {

    private int actionType;

    public HitAction(int actionType){
        this.actionType = actionType;
    }

    public int getActionType(){
        return actionType;
    }

    public abstract void onHit(Hit h);
}
