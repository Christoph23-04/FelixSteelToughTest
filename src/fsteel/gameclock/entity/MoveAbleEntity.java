package fsteel.gameclock.entity;

import fsteel.gameclock.TickAble;
import fsteel.main.Game;
import fsteel.main.GameSettings;
import fsteel.window.border.BorderTouchAction;
import fsteel.window.border.SlidingBorderTouchAction;

import java.util.ArrayList;

public class MoveAbleEntity extends TouchAbleEntity implements TickAble, MovingObject {

    private double totalXDiff;
    private double totalYDiff;
    private Vector2D moveDirection;
    private BorderTouchAction borderTouchAction;

    public MoveAbleEntity(){
        moveDirection = new Vector2D();
        totalXDiff = 0;
        totalYDiff = 0;
        borderTouchAction = new SlidingBorderTouchAction(this);
        super.addTouchAction(borderTouchAction);
    }

    @Override
    public void onTick(float tickPeriod) {
        if(moveDirection.getVectorAmount() < 0.0001){
            return;
        }
        totalXDiff =  moveDirection.getXDir()/GameSettings.CONSTANT_FOR_NORMAL_TPS*tickPeriod;
        totalYDiff = moveDirection.getYDir()/GameSettings.CONSTANT_FOR_NORMAL_TPS*tickPeriod;
        super.setLocation(super.getCorrectXPos() + totalXDiff, super.getCorrectYPos() + totalYDiff);
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

    /**
     * @param bha set to null, if you not want that the entity isn't respecting the border
     */
    public void setBorderTouchAction(BorderTouchAction bha){
        super.removeTouchAction(borderTouchAction);
        borderTouchAction = bha;
        if(borderTouchAction != null){
            super.addTouchAction(borderTouchAction);
        }
    }

    @Override
    public void setSpeedPxS(double speedPxS){
        moveDirection.scaleVector(speedPxS);
    }

    @Override
    public double getSpeedPxS(){
        return moveDirection.getVectorAmount();
    }

    @Override
    public void setMoveDirection(Vector2D moveDirection){
        moveDirection.scaleVector(this.moveDirection.getVectorAmount());
        this.moveDirection = moveDirection;
    }
    @Override
    public Vector2D getMoveDirection(){
        return moveDirection;
    }

    public void setXDir(double xDir){
        setMoveDirection(xDir, moveDirection.getYDir());
    }

    public double getXDir(){
        return moveDirection.getXDir();
    }

    public void setYDir(double yDir){
        setMoveDirection(moveDirection.getXDir(), yDir);
    }

    public double getYDir(){
        return moveDirection.getYDir();
    }

    public void setMoveDirection(double xDir, double yDir){
        moveDirection = new Vector2D(xDir, yDir);
    }

    public boolean isRespectingBorder(){
        return borderTouchAction != null;
    }
}
