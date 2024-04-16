package fsteel.main;

import fsteel.gameclock.entity.HitAbleEntity;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.*;
import fsteel.gameclock.entity.skin.Skin;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        Game.startGame();
        gamePlay();
    }

    //NOT FINAL; ONLY TESTING
    public static void gamePlay(){
        HitEmitter he = new HitEmitter() {
            @Override
            public boolean isHitFor(HitBoxObject hbo) {
                if(hbo.getHitBox().getMaxXOnScreen() > 1920 || hbo.getHitBox().getMinXOnScreen() < 0 ||
                        hbo.getHitBox().getMaxYOnScreen() > 1080 || hbo.getHitBox().getMinYOnScreen() < 0){
                    return true;
                }
                return false;
            }

            @Override
            public Hit getHitForObject(HitBoxObject hbo) {
                if(hbo.getHitBox().getMinXOnScreen() < 0){
                    return new Hit(new Vector2D(1,0), Hit.COLLISION_WITH_BORDER, Hit.FORCE_DEFAULT, this);
                }
                if(hbo.getHitBox().getMaxXOnScreen() > 1920){
                    return new Hit(new Vector2D(-1,0), Hit.COLLISION_WITH_BORDER, Hit.FORCE_DEFAULT, this);
                }
                if(hbo.getHitBox().getMinYOnScreen() < 0){
                    return new Hit(new Vector2D(0,1), Hit.COLLISION_WITH_BORDER, Hit.FORCE_DEFAULT, this);
                }
                if(hbo.getHitBox().getMaxYOnScreen() > 1080){
                    return new Hit(new Vector2D(0,-1), Hit.COLLISION_WITH_BORDER, Hit.FORCE_DEFAULT, this);
                }
                return new Hit(new Vector2D(0,0), -1, 0, this);
            }
        };
        HitCheckProcess.addObject(he);
        for(int i = 0; i < 100; i++) {
            HitAbleEntity tity = new HitAbleEntity();
            tity.addHitAction(new HitAction(Hit.COLLISION_WITH_BORDER) {
                @Override
                public void onHit(Hit h) {
                    if((h.getHitDirection().getXDir() < 0 && tity.getXDir() > 0)||(h.getHitDirection().getXDir() > 0 && tity.getXDir() < 0)){
                        tity.setXDir(-tity.getXDir());
                    }
                    if((h.getHitDirection().getYDir() < 0 && tity.getYDir() > 0)||(h.getHitDirection().getYDir() > 0 && tity.getYDir() < 0)){
                        tity.setYDir(-tity.getYDir());
                    }
                    tity.setSkin(Skin.createBasicSkin(tity.getSimpleXSise(), tity.getSimpleYSise(), new Color((int)(Math.random()*255),
                            (int) (Math.random()*255), (int)(Math.random()*255))));
                }
            });
            tity.setRespectingBoarder(false);
            tity.setMoveDirection(new Vector2D((float) Math.random(), (float) Math.random()));
            tity.setSpeedPxS(800+Math.random()*50);
            tity.appear();
            System.out.println(i);
        }
    }
}
