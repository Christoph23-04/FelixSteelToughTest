package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.entity.Vector2D;

public class Hit {

    public static final int COLLISION_WITH_BORDER = 0;
    public static final int COLLISION_OF_MOVE_ABLE_ENTITY = 1;

    public static final float FORCE_DEFAULT = 1f;

    private float force;
    private Vector2D hitDirection;
    private int hitType;
    private Object hitSource;

    public Hit(Vector2D hitDirection, int hitType, Object hitSource){
        this(hitDirection, hitType, FORCE_DEFAULT, hitSource);
    }

    public Hit(Vector2D hitDirection, int hitType, float force, Object hitSource){
        this.force = force;
        this.hitDirection = hitDirection;
        this.hitType = hitType;
        this.hitSource = hitSource;
    }

    public float getForce(){
        return force;
    }

    public Vector2D getHitDirection(){
        return hitDirection;
    }

    public int getHitType(){
        return hitType;
    }

    public Object getHitSource(){
        return hitSource;
    }
}
