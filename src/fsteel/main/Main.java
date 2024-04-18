package fsteel.main;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.*;
import fsteel.gameclock.entity.physicalEntity.PhysicalEntity;
import fsteel.gameclock.entity.physicalEntity.SpecifiedForce;
import fsteel.gameclock.entity.skin.Skin;
import fsteel.window.border.BorderTouchAction;
import fsteel.window.border.BouncyBorderTouchAction;

import javax.net.ssl.SSLPeerUnverifiedException;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Game.startGame();
        gamePlay();
    }

    //NOT FINAL; ONLY TESTING
    public static void gamePlay(){
        /*for(int i = 0; i < 100; i++) {
            MoveAbleEntity tity = new MoveAbleEntity();
            tity.setMoveDirection(new Vector2D(Math.random(), Math.random()));
            tity.setBorderTouchAction(new BouncyBorderTouchAction(tity));
            tity.setSpeedPxS(400+Math.random()*50);
            tity.appear();
            System.out.println(i);
        }
        */

        for(int i = 0; i < 190; i++){
            PhysicalEntity pe = new PhysicalEntity();
            pe.setLocation(i*10,1080);
            pe.setBorderTouchAction(new BouncyBorderTouchAction(pe));
            pe.setMass((double)i/10D+1);
            pe.setSpeedPxS(0);
            pe.appear();
            pe.applyForce(new SpecifiedForce(100, 2, new Vector2D(0,-1)));
        }
    }
}
