package fsteel.gameclock;

import fsteel.main.Game;
import fsteel.main.GameSettings;

import java.util.ArrayList;

public class GameTickProcess extends GameClockProcess{
    private final ArrayList<TickAble> tickAbles;


    public GameTickProcess() {
        super(GameSettings.TARGET_TICK_FPS);
        tickAbles = new ArrayList<TickAble>();
    }

    @Override
    protected void onTick(float lastTickRatio) {
        for(TickAble ta : tickAbles){
            ta.onTick(lastTickRatio);
        }
    }

    public void addTickAble(TickAble ta){
        try {
            super.getProcessScheduleObject().lock();
            tickAbles.add(ta);
        }
        finally{
            super.getProcessScheduleObject().unlock();
        }
    }

    public void removeTickAble(TickAble ta){
        try{
            super.getProcessScheduleObject().lock();
            tickAbles.remove(ta);
        }
        finally{
            super.getProcessScheduleObject().unlock();
        }
    }

    public boolean isTicked(TickAble ta){
        return tickAbles.contains(ta);
    }
}
