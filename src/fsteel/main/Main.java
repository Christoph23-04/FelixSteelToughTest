package fsteel.main;

import fsteel.gameclock.entity.ScreenPoint;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.*;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Game.startGame();
        gamePlay();
    }

    //NOT FINAL; ONLY TESTING
    public static void gamePlay(){
        HitAbleEntity tity = new HitAbleEntity();
        tity.addHitAction(new HitAction(Hit.COLLISION_WITH_BORDER) {
            @Override
            public void onHit(Hit h) {
                tity.setMoveDirection(h.getHitDirection());
                System.out.println("x: " + tity.getCorrectXPos() + " - y: " +  tity.getCorrectYPos());
            }
        });
        tity.setMoveDirection(new Vector2D(5f, (float) Math.random()*4));
        tity.setSpeedPxS(300);
        tity.appear();

        HitEmitter he = new HitEmitter() {
            @Override
            public boolean isHitFor(HitBoxObject hbo) {
                if(hbo.getXPosOnScreen() > 700 || hbo.getXPosOnScreen() < 0){
                    return true;
                }
                return false;
            }

            @Override
            public Hit getHitForObject(HitBoxObject hbo) {
                if(hbo.getXPosOnScreen() < 0){
                    return new Hit(new Vector2D(1,0), Hit.COLLISION_WITH_BORDER, Hit.FORCE_DEFAULT, this);
                }
                else{
                    return new Hit(new Vector2D(-1,0), Hit.COLLISION_WITH_BORDER, Hit.FORCE_DEFAULT, this);
                }
            };
        };
        HitCheckProcess.addObject(he);
    }
}
