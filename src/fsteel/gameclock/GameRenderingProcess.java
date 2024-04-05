package fsteel.gameclock;

import fsteel.gameclock.GameClockProcess;
import fsteel.gameclock.rendering.RenderCanvas;
import fsteel.main.GameSettings;

public class GameRenderingProcess extends GameClockProcess {

    private RenderCanvas renderingCanvas;

    public GameRenderingProcess(RenderCanvas renderCanvas){
        super(GameSettings.TARGET_RENDER_FPS);
        this.renderingCanvas = renderCanvas;
    }

    public synchronized void setRenderingCanvas(RenderCanvas renderingCanvas){
        this.renderingCanvas = renderingCanvas;
    }

    public synchronized RenderCanvas getRenderingCanvas(){
        return renderingCanvas;
    }

    @Override
    protected void onTick(float lastTickRatio) {
        renderingCanvas.renderAll();
    }
}
