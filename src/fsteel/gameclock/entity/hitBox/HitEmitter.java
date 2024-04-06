package fsteel.gameclock.entity.hitBox;

public interface HitEmitter {

    public boolean isHitFor(HitBoxObject hbo);
    public Hit getHitForObject(HitBoxObject hbo);

}
