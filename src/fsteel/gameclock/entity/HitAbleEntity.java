package fsteel.gameclock.entity;

import fsteel.gameclock.entity.hitBox.*;
import fsteel.gameclock.entity.skin.Skin;
import fsteel.util.ArrayListMap;
import fsteel.window.border.BorderHitAction;

public class HitAbleEntity extends MoveAbleEntity implements HitBoxObject, HitEmitter {

    private HitBox hitBox;
    private final ArrayListMap<Integer, HitAction> hitActions;
    private boolean areBoundsEqualsToSkin;
    private boolean isRespectingBoarder;
    private BorderHitAction boarderHitAction;

    public HitAbleEntity() {
        hitBox = null;
        hitActions = new ArrayListMap<Integer, HitAction>();
        areBoundsEqualsToSkin = true;
        if(super.getSkin() != null){
            hitBox = super.getSkin().getAptHitBox(this);
        }
        isRespectingBoarder = false;
        boarderHitAction = BorderHitAction.
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
    public boolean doesRecognizeHitType(int hitType) {
        return hitActions.containsKey(hitType);
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

    public void setRespectingBoarder(boolean isEntityRespectingBoarder){
        this.isRespectingBoarder = isEntityRespectingBoarder;
        if(!isRespectingBoarder){
            removeHitAction(boarderHitAction);
            return;
        }
        addHitAction(boarderHitAction);
    }

    public void setBoarderHitAction(BorderHitAction bha){

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
        if(!hbo.doesRecognizeHitType(Hit.COLLISION_OF_MOVE_ABLE_ENTITY)){
            return false;
        }
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
