package fsteel.gameclock.entity;

public abstract class HitAction {

    private String actionName;

    public HitAction(String actionName){
        this.actionName = actionName;
    }

    public boolean isAction(String actionName){
        return actionName.equals(this.actionName);
    }

    public String getActionName(){
        return actionName;
    }

    public abstract void onHit();
}
