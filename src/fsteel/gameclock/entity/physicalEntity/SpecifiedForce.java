package fsteel.gameclock.entity.physicalEntity;

import fsteel.gameclock.entity.Vector2D;

public class SpecifiedForce{

    private double forceAmount;
    private double duration;

    public SpecifiedForce(double forceAmount, double duration, Vector2D direction){
        this.forceAmount = forceAmount;
        this.duration = duration;
        this.direction = direction;
    }

    public double getTotalForce(){
        return forceAmount*duration;
    }

    public double getForceAndReduceDuration(double impactedDuration){
        if(duration > impactedDuration){
            duration = duration - impactedDuration;
        }
        else{
            impactedDuration = duration;
            duration = 0;
        }
        return forceAmount*impactedDuration;
    }

    public double getDuration(){
        return duration;
    }

    public double getForce(){
        return forceAmount;
    }

    private Vector2D direction;

    public Vector2D getDirection(){
        return direction;
    }
}
