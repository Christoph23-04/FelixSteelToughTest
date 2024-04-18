package fsteel.gameclock;

import java.util.ArrayList;

public interface GameProcess {

    public static final int ORIGIN_GAME_START = 0;
    public static final int GAME_START_AFTER_BREAK = 1;
    public static final int PROCESS_START_EXTERNAL = 2;
    public static final int GAME_STOP_THROUGH_WINDOW = 100;
    public static final int GAME_STOP_FOR_BREAK = 101;
    public static final int PROCESS_STOP_EXTERNAL = 102;
    public static final int GAME_STOP_TROUGH_BUTTON = 103;

    public void onProcessStart(int startType);
    public void onProcessStop(int stopType);
    public boolean isProcessRunning();
    public long getProcessStartTime();
    public void destroyProcess();
    public Thread getProcessThread();
}
