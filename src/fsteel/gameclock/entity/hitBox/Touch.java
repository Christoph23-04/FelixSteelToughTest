package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.Vector2D;

public class Touch {

    public static final int COLLISION_WITH_BORDER = 0;


    public static final double FORCE_DEFAULT = 0;


    private double force;
    private Vector2D touchDirection;
    private int touchType;
    private TouchEmitter touchSource;

    public Touch(Vector2D touchDirection, double force, int touchType, TouchEmitter touchSource){
        this.touchDirection = touchDirection;
        this.force = force;
        this.touchType = touchType;
        this.touchSource = touchSource;
    }

    public Vector2D getTouchDirection(){
        return touchDirection;
    }

    public double getForce(){
        return force;
    }

    public int getTouchType(){
        return touchType;
    }

    public TouchEmitter getTouchSource(){
        return touchSource;
    }

}
