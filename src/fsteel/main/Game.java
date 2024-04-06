package fsteel.main;

import fsteel.gameclock.GameProcess;
import fsteel.gameclock.GameProcessManager;
import fsteel.gameclock.GameRenderingProcess;
import fsteel.gameclock.GameTickProcess;
import fsteel.gameclock.entity.hitBox.HitCheckProcess;
import fsteel.gameclock.rendering.ScreenObjectRenderer;
import fsteel.keylistening.GameKeyBinding;
import fsteel.window.GameFrame;
import fsteel.window.GamePane;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private static GameFrame gameFrame;
    private static ScreenObjectRenderer renderer;
    private static GameRenderingProcess renderProcess;
    private static HitCheckProcess hitCheckProcess;
    private static GameTickProcess tickProcess;

    public static void startGame(){
        renderer = new ScreenObjectRenderer();
        gameFrame = new GameFrame(renderer);
        startProcesses();
    }

    private static void startProcesses(){
        renderProcess = new GameRenderingProcess(gameFrame.getGamePane());
        tickProcess = new GameTickProcess();
        hitCheckProcess = new HitCheckProcess();
        GameProcessManager.startGameProcesses(GameProcess.ORIGIN_GAME_START);
        startSpecsPrint();
    }

    //TODO
    private static void startSpecsPrint(){
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(renderProcess.getCalculatedTPS() + "  FPS Render Process - rendered");
                System.out.println(tickProcess.getCalculatedTPS() + "  TPS Tick Process - ticked");
                System.out.println(hitCheckProcess.getCalculatedTPS() + "  TPS Hit Check - ticked");
            }
        }, 1000, 1000);
    }

    public static GameFrame getGameFrame(){
        return gameFrame;
    }

    public static GamePane getBackground(){
        return gameFrame.getGamePane();
    }

    public static ScreenObjectRenderer getRenderer() {
        return renderer;
    }

    public static GameRenderingProcess getRenderProcess(){
        return renderProcess;
    }

    public static GameTickProcess getTickProcess(){
        return tickProcess;
    }

    public static HitCheckProcess getHitCheckProcess(){
        return hitCheckProcess;
    }

    public static void addGameKeyBinding(GameKeyBinding gkb){
        gameFrame.getActionManager().addGameKeyBinding(gkb);
    }

    public static void removeGameKeyBinding(GameKeyBinding gkb){
        gameFrame.getActionManager().removeGameKeyBinding(gkb);
    }
}
