package fsteel.gameclock.entity.hitBox;

import fsteel.gameclock.GameClockProcess;
import fsteel.gameclock.entity.TouchAbleEntity;
import fsteel.main.Game;
import fsteel.main.GameSettings;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TouchCheckProcess extends GameClockProcess {

    private static final ArrayList<TouchAbleEntity> touchAbleEntities = new ArrayList<TouchAbleEntity>();

    public static void addTouchAbleEntity(TouchAbleEntity ta){
        Lock lock = Game.getTouchCheckProcess().getProcessScheduleObject();
        Game.getTouchCheckProcess().requestLockTime();
        try{
            lock.lock();
            touchAbleEntities.add(ta);
        }
        finally {
            lock.unlock();
        }
    }
    public static void removeTouchAbleEntity(TouchAbleEntity ha){
        ReentrantLock lock = Game.getTouchCheckProcess().getProcessScheduleObject();
        Game.getTouchCheckProcess().requestLockTime();
        try{
            lock.lock();
            touchAbleEntities.remove(ha);
        }
        finally {
            lock.unlock();
        }
    }

    private static final ArrayList<TouchEmitter> tEmitter = new ArrayList<TouchEmitter>();

    public static void addTouchEmitter(TouchEmitter te) {
        Lock lock = Game.getTouchCheckProcess().getProcessScheduleObject();
        Game.getTouchCheckProcess().requestLockTime();
        try {
            lock.lock();
            tEmitter.add(te);
        } finally {
            lock.unlock();
        }
    }

    public static void removeTouchEmitter(TouchEmitter te){
        Lock lock = Game.getTouchCheckProcess().getProcessScheduleObject();
        Game.getTouchCheckProcess().requestLockTime();
        try{
            lock.lock();
            tEmitter.remove(te);
        }
        finally {
            lock.unlock();
        }
    }

    public TouchCheckProcess(){
        super(GameSettings.TARGET_TOUCH_TEST_TPS);
    }

    @Override
    protected void onTick(float lastTickDeviation) {
        for (TouchAbleEntity tae : touchAbleEntities) {
            checkIfStillTouched(tae);
            checkIfNewTouchHappened(tae);
        }
    }

    private void checkIfStillTouched(TouchAbleEntity tae){
        ArrayList<Touch> currentTouches = tae.getCurrentTouches();
        for(int i = 0; i < currentTouches.size(); i++){
            TouchEmitter te = currentTouches.get(i).getTouchSource();
            if(!tEmitter.contains(te) || te.isTouching(tae) == TouchEmitter.NOT_TOUCHED){
                tae.onTouchEnded(currentTouches.get(i));
                i--;
            }
        }
    }

    private void checkIfNewTouchHappened(TouchAbleEntity tae){
        ArrayList<Touch> currentTouches = tae.getCurrentTouches();
        for(TouchEmitter te : tEmitter){
            boolean containedTouch = false;
            for(Touch t : currentTouches){
                if(t.getTouchSource() == te){
                    containedTouch = true;
                    break;
                }
            }
            if(containedTouch){
                continue;
            }
            int touchCase = te.isTouching(tae);
            if(touchCase != TouchEmitter.NOT_TOUCHED){
                Touch t = te.getTouchFor(tae, touchCase);
                if(t != null){
                    tae.onTouch(t);
                }
                else{
                    //TODO implement error handling
                }
            }
        }
    }
}
