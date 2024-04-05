package fsteel.gameclock;

import java.util.ArrayList;

public class GameProcessManager {

    private static GameProcessManager gameProcessManager = new GameProcessManager();

    public static void registerProcess(GameProcess gp){
        gameProcessManager.addGameProcess(gp);
    }

    public static void removeProcess(GameProcess gp){
        gameProcessManager.removeGameProcess(gp);
    }

    public static void startGameProcesses(int startReason){
        gameProcessManager.startGame(startReason);
    }

    public static void stopGameProcesses(int stopReason){
        gameProcessManager.stopGame(stopReason);
    }

    public static boolean isGameRunning(){
        return gameProcessManager.isGameRunning;
    }

    private boolean isGameRunning;
    private final ArrayList<GameProcess> processes;

    private GameProcessManager(){
        isGameRunning = false;
        processes = new ArrayList<GameProcess>();
    }

    public void addGameProcess(GameProcess gp){
        if(isGameRunning && !gp.isProcessRunning()){
            gp.onProcessStart(GameProcess.PROCESS_START_EXTERNAL);
        }
        processes.add(gp);
    }

    public void removeGameProcess(GameProcess gp){
        if(processes.contains(gp) && gp.isProcessRunning()){
            gp.onProcessStop(GameProcess.PROCESS_STOP_EXTERNAL);
        }
        processes.remove(gp);
    }

    public void startGame(int startType){
        if(isGameRunning){
            return;
        }
        for(GameProcess gp : processes){
            if(!gp.isProcessRunning()){
                gp.onProcessStart(startType);
            }
        }
        isGameRunning = true;
    }

    public void stopGame(int stopType){
        if(!isGameRunning){
            return;
        }
        for(GameProcess gp : processes){
            if(gp.isProcessRunning()){
                gp.onProcessStop(stopType);
            }
        }
        isGameRunning = false;
    }
}
