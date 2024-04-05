package fsteel.gameclock;

import fsteel.main.GameSettings;

public abstract class GameClockProcess extends AbstractGameProcess{

    public static final long NANOS_IN_MILLIS = 1000000;
    public static final long NANOS_IN_SECOND = 1000000000;
    public static final int TPS_CALCULATION_SAVE_LENGTH = 10;
    public static final int MAX_TICK_OFFSET_NANOS = 100000;
    private final long normNanosPerTick;

    private int targetTPS;
    private long targetNanosPerTick;


    private final long[] pastTickTimes;
    private long tickOffsetNanos;

    public GameClockProcess(int targetTPS){
        normNanosPerTick = NANOS_IN_SECOND / GameSettings.CONSTANT_FOR_NORMAL_TPS;
        pastTickTimes = new long[TPS_CALCULATION_SAVE_LENGTH];
        tickOffsetNanos = 0;
        setTargetTPS(targetTPS);
    }

    public void setTargetTPS(int targetTPS){
        this.targetTPS = targetTPS;
        targetNanosPerTick = NANOS_IN_SECOND/targetTPS;
    }

    public int getTargetTPS(){
        return targetTPS;
    }

    @Override
    protected void externalProcess() {
        long timeBeforeTick;
        long timeAfterTick = System.nanoTime();
        long tickDuration = targetNanosPerTick;
        long sleepTime;
        float lastNormTickRatio;

        while(isProcessRunning()){
            timeBeforeTick = System.nanoTime();
            lastNormTickRatio = (float)normNanosPerTick/(float)(tickDuration + (timeBeforeTick - timeAfterTick));

            bufferTickTime(timeBeforeTick);
            onTick(lastNormTickRatio);

            timeAfterTick = System.nanoTime();
            tickDuration = timeAfterTick - timeBeforeTick;

            if(tickDuration > targetNanosPerTick){
                if(tickOffsetNanos < MAX_TICK_OFFSET_NANOS){
                    tickOffsetNanos =+ tickDuration - targetNanosPerTick;
                }
                continue;
            }

            sleepTime = targetNanosPerTick - tickDuration;
            if(tickOffsetNanos > sleepTime){
                tickOffsetNanos =- sleepTime;
                continue;
            }

            sleepTime = sleepTime - tickOffsetNanos;
            tickOffsetNanos = 0;

            try {
                Thread.sleep((int)(sleepTime/NANOS_IN_MILLIS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void bufferTickTime(long tickTime){
        for(int i = pastTickTimes.length - 1; i > 0; i--){
            pastTickTimes[i] = pastTickTimes[i-1];
        }
        pastTickTimes[0] = tickTime;
    }

    public float getCalculatedTPS(){
        long timePeriodEnd = pastTickTimes[0];
        long timePeriodStart = pastTickTimes[pastTickTimes.length-1];
        int foundTicks = 0;
        for(int i = pastTickTimes.length -1; i >= 0; i--){
            if(pastTickTimes[i] > 0){
                foundTicks++;
                if(timePeriodStart == 0){
                    timePeriodStart = pastTickTimes[i];
                }
            }
        }
        long nanosPerTick = (timePeriodEnd - timePeriodStart)/foundTicks;
        return (float) NANOS_IN_SECOND/nanosPerTick;
    }

    protected abstract void onTick(float lastTickRatio);
}
