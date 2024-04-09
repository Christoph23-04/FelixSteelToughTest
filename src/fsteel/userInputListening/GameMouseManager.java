package fsteel.userInputListening;

import fsteel.main.Game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameMouseManager extends MouseAdapter {

    private final ArrayList<GameMouseListener> mouseListeners;
    private boolean isMousePressed;

    public GameMouseManager(){
        mouseListeners = new ArrayList<GameMouseListener>();
        isMousePressed = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(isMousePressed){
            return;
        }
        int xPosOnScreen = (int)(e.getX() / Game.getRenderer().getCurrentWidthOffset());
        int yPosOnScreen = (int)(e.getY() / Game.getRenderer().getCurrentHeightOffset());
        executeMousePressedListeners(xPosOnScreen, yPosOnScreen);
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
        int xPosOnScreen = (int)(e.getX() / Game.getRenderer().getCurrentWidthOffset());
        int yPosOnScreen = (int)(e.getY() / Game.getRenderer().getCurrentHeightOffset());
        executeMouseReleasedListeners(xPosOnScreen, yPosOnScreen);
    }

    public void addMouseListener(GameMouseListener l){
        mouseListeners.add(l);
    }

    public void removeMouseListener(GameMouseListener l){
        mouseListeners.remove(l);
    }

    private void executeMousePressedListeners(int xPosOnScreen, int yPosOnScreen){
        for(GameMouseListener ml : mouseListeners){
            ml.onMousePressed(xPosOnScreen, yPosOnScreen);
        }
        System.out.println("Pressed " + xPosOnScreen + " - " + yPosOnScreen);
    }

    private void executeMouseReleasedListeners(int xPosOnScreen, int yPosOnScreen){
        for(GameMouseListener ml : mouseListeners){
            ml.onMouseReleased(xPosOnScreen, yPosOnScreen);
        }
        System.out.println("Released " + xPosOnScreen + " - " + yPosOnScreen);
    }
}
