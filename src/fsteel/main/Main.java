package fsteel.main;

import fsteel.gameclock.entity.MovableEntity;
import fsteel.gameclock.entity.Vector2D;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args){
        Game.startGame();
        gamePlay();
    }

    //NOT FINAL; ONLY TESTING
    public static void gamePlay(){
        MovableEntity tity = new MovableEntity();
        tity.setMoveDirection(new Vector2D(5f, 2f));
        tity.setSpeedPxS(220);
        tity.appear();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                tity.setXDir(-tity.getXDir());
            }
        }, 5000);
    }
}
