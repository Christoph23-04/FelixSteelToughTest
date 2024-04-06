package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.Skin;
import fsteel.gameclock.entity.Vector2D;
import fsteel.util.ArrayListMap;

public class HitAbleEntity extends MoveAbleEntity implements HitBoxObject, HitEmitter {

    private HitBox hitBox;
    private ArrayListMap<Integer, HitAction> hitActions;
    private boolean areBoundsEqualsToSkin;

    public HitAbleEntity() {
        hitBox = null;
        hitActions = new ArrayListMap<Integer, HitAction>();
        areBoundsEqualsToSkin = true;
        if(super.getSkin() != null){
            hitBox = super.getSkin().getAptHitBox(this);
        }
    }

    @Override
    public void appear() {
        super.appear();
        HitCheckProcess.addObject(this);
    }

    @Override
    public void disappear() {
        super.disappear();
        HitCheckProcess.removeObject(this);
    }

    @Override
    public void onHit(Hit hit) {
        if (!hitActions.containsKey(hit.getHitType())) {
            return;
        }
        for (HitAction hitAction : hitActions.get(hit.getHitType())) {
            hitAction.onHit(hit);
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

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public boolean isHitFor(HitBoxObject hbo) {
        return hbo.getHitBox().isHit(hitBox);
    }

    @Override
    public Hit getHitForObject(HitBoxObject hbo) {
        Vector2D hitVector = hbo.getHitBox().calculateVectorToCenter(this.getHitBox());
        return new Hit(hitVector, Hit.COLLISION_OF_MOVE_ABLE_ENTITY, this);
    }

    public void addHitAction(HitAction ha){
        hitActions.putObject(ha.getActionType(), ha);
    }

    public void removeHitAction(HitAction ha){
        hitActions.removeObject(ha.getActionType(), ha);
    }
}
