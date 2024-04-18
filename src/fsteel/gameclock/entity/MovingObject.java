package fsteel.gameclock.entity;

public interface MovingObject{

    public void setSpeedPxS(double speed);
    public double getSpeedPxS();
    public void setMoveDirection(Vector2D direction);
    public Vector2D getMoveDirection();

}
