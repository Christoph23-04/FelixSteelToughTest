package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.GameClockProcess;
import fsteel.main.Game;
import fsteel.main.GameSettings;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class HitCheckProcess extends GameClockProcess {

    public static void addObject(Object o){
        if(o instanceof HitBoxObject){
            HitBoxObjects.addHitBoxObject((HitBoxObject) o);
        }
        if(o instanceof HitEmitter){
            HitEmitterObjects.addHitEmitter((HitEmitter) o);
        }
    }

    public static void removeObject(Object o){
        if(o instanceof HitBoxObject){
            HitBoxObjects.removeHitBoxObject((HitBoxObject) o);
        }
        if(o instanceof HitEmitter){
            HitEmitterObjects.removeHitEmitter((HitEmitter) o);
        }
    }

    public static class HitBoxObjects{
        private static final ArrayList<HitBoxObject> hboObjects = new ArrayList<HitBoxObject>();

        public static void addHitBoxObject(HitBoxObject ha){
            Lock lock = Game.getHitCheckProcess().getProcessScheduleObject();
            try{
                lock.lock();
                hboObjects.add(ha);
            }
            finally {
                lock.unlock();
            }
        }
        public static void removeHitBoxObject(HitBoxObject ha){
            Lock lock = Game.getHitCheckProcess().getProcessScheduleObject();
            try{
                lock.lock();
                hboObjects.remove(ha);
            }
            finally {
                lock.unlock();
            }
        }
    }

    public static class HitEmitterObjects{
        private static final ArrayList<HitEmitter> hEmitter = new ArrayList<HitEmitter>();

        public static void addHitEmitter(HitEmitter he){
            Lock lock = Game.getHitCheckProcess().getProcessScheduleObject();
            try{
                lock.lock();
                hEmitter.add(he);
            }
            finally {
                lock.unlock();
            }
        }

        public static void removeHitEmitter(HitEmitter he){
            Lock lock = Game.getHitCheckProcess().getProcessScheduleObject();
            try{
                lock.lock();
                hEmitter.remove(he);
            }
            finally {
                lock.unlock();
            }
        }
    }

    public HitCheckProcess(){
        super(GameSettings.TARGET_COLLISION_TEST_TPS);
    }

    @Override
    protected void onTick(float lastTickRatio) {
        for(HitEmitter emitter : HitEmitterObjects.hEmitter){
            for(HitBoxObject receiver : HitBoxObjects.hboObjects){
                if(receiver == emitter){
                    continue;
                }
                if(!emitter.isHitFor(receiver)){
                    continue;
                }
                receiver.onHit(emitter.getHitForObject(receiver));
            }
        }
    }
}
