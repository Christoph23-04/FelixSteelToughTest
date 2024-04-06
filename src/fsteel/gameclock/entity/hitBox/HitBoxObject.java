package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.ScreenPoint;

public interface HitBoxObject extends ScreenPoint {

    public void onHit(Hit hit);
    public HitBox getHitBox();
}
