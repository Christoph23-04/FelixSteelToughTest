package fsteel.window.border;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.MovingObject;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.Touch;
import fsteel.gameclock.entity.hitBox.TouchAction;

public abstract class BorderTouchAction implements TouchAction {

    private boolean isCurrentlyInBorder;
    protected MovingObject touchReceiver;

    public BorderTouchAction(MovingObject touchReceiver) {
        isCurrentlyInBorder = false;
        this.touchReceiver = touchReceiver;
    }

    @Override
    public int getTouchType() {
        return Touch.COLLISION_WITH_BORDER;
    }

    @Override
    public void onTouch(Touch t) {
        isCurrentlyInBorder = true;
        int touchDirection = t.getTouchDirection().toVectorDirection();
        if(touchDirection == Vector2D.VECTOR_DIRECTION_LEFT && touchReceiver.getMoveDirection().getXDir() > 0){
            onVerticalBorderTouched(t);
        }
        if(touchDirection == Vector2D.VECTOR_DIRECTION_RIGHT && touchReceiver.getMoveDirection().getXDir() < 0){
            onVerticalBorderTouched(t);
        }
        if(touchDirection == Vector2D.VECTOR_DIRECTION_UP && touchReceiver.getMoveDirection().getYDir() > 0){{
            onHorizontalBorderTouched(t);
        }}
        if(touchDirection == Vector2D.VECTOR_DIRECTION_DOWN && touchReceiver.getMoveDirection().getYDir() < 0){
            onHorizontalBorderTouched(t);
        }
    }

    @Override
    public void onTouchEnded(Touch t) {
        isCurrentlyInBorder = false;
    }

    public boolean isCurrentlyInBorder(){
        return isCurrentlyInBorder;
    }

    public MovingObject getTouchReceiver(){
        return touchReceiver;
    }

    public abstract void onVerticalBorderTouched(Touch t);
    public abstract void onHorizontalBorderTouched(Touch t);
}
