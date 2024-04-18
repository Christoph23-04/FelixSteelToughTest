package fsteel.window.border;

import fsteel.gameclock.entity.MovingObject;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.Touch;

public class BouncyBorderTouchAction extends BorderTouchAction{

    public BouncyBorderTouchAction(MovingObject touchReceiver) {
        super(touchReceiver);
    }

    @Override
    public void onVerticalBorderTouched(Touch t) {
        double newXDir = - touchReceiver.getMoveDirection().getXDir();
        touchReceiver.setMoveDirection(new Vector2D(newXDir, touchReceiver.getMoveDirection().getYDir()));
    }

    @Override
    public void onHorizontalBorderTouched(Touch t) {
        double newYDir = - touchReceiver.getMoveDirection().getYDir();
        touchReceiver.setMoveDirection(new Vector2D(touchReceiver.getMoveDirection().getXDir(), newYDir));
    }
}
