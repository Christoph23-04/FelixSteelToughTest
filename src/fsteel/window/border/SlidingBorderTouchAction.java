package fsteel.window.border;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.MovingObject;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.Touch;

public class SlidingBorderTouchAction extends BorderTouchAction {

    private boolean shouldReduceSpeed;

    public SlidingBorderTouchAction(MovingObject touchReceiver){
        super(touchReceiver);
        shouldReduceSpeed = true;
    }

    @Override
    public void onVerticalBorderTouched(Touch t) {
        double oldAmount = touchReceiver.getMoveDirection().getVectorAmount();
        touchReceiver.setMoveDirection(new Vector2D(0, touchReceiver.getMoveDirection().getYDir()));
        touchReceiver.setSpeedPxS(calculateSpeed(oldAmount, touchReceiver.getMoveDirection().getVectorAmount()));
    }

    @Override
    public void onHorizontalBorderTouched(Touch t) {
        double oldAmount = touchReceiver.getMoveDirection().getVectorAmount();
        touchReceiver.setMoveDirection(new Vector2D(touchReceiver.getMoveDirection().getXDir(), 0));
        touchReceiver.setSpeedPxS(calculateSpeed(oldAmount, touchReceiver.getMoveDirection().getVectorAmount()));
    }

    private double calculateSpeed(double oldVecAmount, double newVecAmount){
        if(shouldReduceSpeed){
            return touchReceiver.getSpeedPxS()*(newVecAmount/oldVecAmount);
        }
        return touchReceiver.getSpeedPxS();
    }

    public void setShouldReduceSpeed(boolean shouldReduceSpeed){
        this.shouldReduceSpeed = shouldReduceSpeed;
    }

    public boolean isReducingSpeed(){
        return shouldReduceSpeed;
    }
}
