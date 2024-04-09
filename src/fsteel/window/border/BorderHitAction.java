package fsteel.window.border;

import fsteel.gameclock.entity.HitAbleEntity;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.Hit;
import fsteel.gameclock.entity.hitBox.HitAction;

public abstract class BorderHitAction extends HitAction {

    public static BorderHitAction createDefaultBoarderHitActionForEntity(HitAbleEntity hae){
        BorderHitAction bha = new BorderHitAction() {
            @Override
            public void onHit(Hit h) {
                hae.setSpeedPxS(0);
            }
        };
        return bha;
    }

    public BorderHitAction() {
        super(Hit.COLLISION_WITH_BORDER);
    }
}
