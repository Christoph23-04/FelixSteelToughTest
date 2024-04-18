package fsteel.gameclock.entity.physicalEntity;

import fsteel.gameclock.entity.MoveAbleEntity;
import fsteel.gameclock.entity.TouchAbleEntity;
import fsteel.gameclock.entity.Vector2D;
import fsteel.gameclock.entity.hitBox.Touch;
import fsteel.gameclock.entity.hitBox.TouchCheckProcess;
import fsteel.gameclock.entity.hitBox.TouchEmitter;
import fsteel.main.GameSettings;

import java.util.ArrayList;

public class PhysicalEntity extends MoveAbleEntity implements PhysicalObject{

    public static final double DEFAULT_MASS = 1;

    private double mass;
    private ArrayList<SpecifiedForce> influencingForces;

    public PhysicalEntity(){
        mass = DEFAULT_MASS;
        influencingForces = new ArrayList<SpecifiedForce>();
    }

   @Override
   public void onTick(float tickTime){
        super.onTick(tickTime);
        for(int i = 0; i < influencingForces.size(); i++){
            if(influencingForces.get(i).getDuration() <= 0){
                influencingForces.remove(i);
                i--;
                continue;
            }
            float tickedSeconds = tickTime*(1f/(float)GameSettings.CONSTANT_FOR_NORMAL_TPS);
            SpecifiedForce f = influencingForces.get(i);
            applySingleForce(f.getForceAndReduceDuration(tickedSeconds), f.getDirection());
        }
   }

    /**
     * The speed change is calculated through Math.sqrt(2*force/mass)
     */
   public void applySingleForce(double force, Vector2D direction){
        direction.scaleVector(Math.sqrt(2*force/mass));
        super.getMoveDirection().addVector(direction);
   }

    public void setMass(double mass){
        this.mass = Math.abs(mass);
    }

    @Override
    public double getMass(){
        return mass;
    }

    public void applyForce(SpecifiedForce sf){
        influencingForces.add(sf);
    }

    public void removeForce(SpecifiedForce sf){
        influencingForces.remove(sf);
    }
}
