package fsteel.window;

import fsteel.gameclock.entity.ScreenObject;
import fsteel.gameclock.rendering.GraphicRenderer;
import fsteel.gameclock.rendering.RenderCanvas;
import fsteel.gameclock.rendering.ScreenObjectRenderer;
import fsteel.main.GameSettings;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePane extends Canvas implements RenderCanvas {

    public static final int BUFFER_AMOUNT = 2;

    private final ScreenObjectRenderer renderer;

    public GamePane(ScreenObjectRenderer renderer){
        this.renderer = renderer;
        super.setIgnoreRepaint(true);
    }

    @Override
    public boolean renderAll() {
        try {
            long l = System.currentTimeMillis();
            BufferStrategy bs = super.getBufferStrategy();
            Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
            renderer.renderObject(g2d);
            g2d.dispose();
            bs.show();
            return true;
        }
        finally{
            return false;
        }
    }
}
