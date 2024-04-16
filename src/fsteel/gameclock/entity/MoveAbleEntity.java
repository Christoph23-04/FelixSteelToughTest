package fsteel.gameclock.entity;

import fsteel.gameclock.TickAble;
import fsteel.main.Game;
import fsteel.main.GameSettings;

public class MoveAbleEntity extends Entity implements TickAble {

    private float totalXDiff;
    private float totalYDiff;

    private Vector2D moveDirection;
    private double speedPxS;

    public MoveAbleEntity(){
        moveDirection = new Vector2D();
        speedPxS = 0;
        totalXDiff = 0;
        totalYDiff = 0;
    }

    @Override
    public void appear(){
        super.appear();
        Game.getTickProcess().addTickAble(this);
    }

    @Override
    public void disappear(){
        super.disappear();
        Game.getTickProcess().removeTickAble(this);
    }

    @Override
    public void onTick(float tickPeriod) {
        if(speedPxS < 0.00001 | moveDirection.getVectorAmount() < 0.00001){
            return;
        }
        float distanceForTick = (float)(speedPxS/((float)GameSettings.CONSTANT_FOR_NORMAL_TPS)*tickPeriod);
        totalXDiff =  (float)moveDirection.getXDirToAmount(distanceForTick);
        totalYDiff = (float)moveDirection.getYDirToAmount(distanceForTick);
        super.setPos(super.getCorrectXPos() + totalXDiff, super.getCorrectYPos() + totalYDiff);
    }

    public void setMoveDirection(double xDir, double yDir){
        moveDirection.setDirection(xDir, yDir);
    }

    public void setXDir(double xDir){
        setMoveDirection(xDir, moveDirection.getYDir());
    }

    public void setYDir(double yDir){
        setMoveDirection(moveDirection.getXDir(), yDir);
    }

    public void setMoveDirection(Vector2D moveDirection){
        this.moveDirection = moveDirection;
    }

    public Vector2D getMoveDirection(){
        return moveDirection;
    }

    public double getXDir(){
        return moveDirection.getXDir();
    }

    public double getYDir(){
        return moveDirection.getYDir();
    }

    public void setSpeedPxS(double speedPxS){
        this.speedPxS = speedPxS;
    }

    public double getSpeedPxS(){
        return speedPxS;
    }
}
