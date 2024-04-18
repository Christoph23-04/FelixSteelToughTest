package fsteel.main;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.*;
import fsteel.gameclock.entity.skin.Skin;
import fsteel.window.border.BorderTouchAction;
import fsteel.window.border.BouncyBorderTouchAction;

import java.awt.*;

public class Main {

    public static void main(String[] args){
        Game.startGame();
        gamePlay();
    }

    //NOT FINAL; ONLY TESTING
    public static void gamePlay(){
        for(int i = 0; i < 100; i++) {
            MoveAbleEntity tity = new MoveAbleEntity();
            tity.setMoveDirection(new Vector2D(Math.random(), Math.random()));
            tity.setBorderTouchAction(new BouncyBorderTouchAction(tity));
            tity.setSpeedPxS(400+Math.random()*50);
            tity.appear();
            System.out.println(i);
        }
    }
}
