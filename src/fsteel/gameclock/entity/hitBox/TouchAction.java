package fsteel.gameclock.entity.hitBox;

public interface TouchAction {

    public int getTouchType();
    public void onTouch(Touch t);
    public void onTouchEnded(Touch t);

}
