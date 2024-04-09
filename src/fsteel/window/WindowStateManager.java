package fsteel.window;

import fsteel.userInputListening.GameKeyBindingAdapter;

import javax.swing.*;
import java.awt.*;

public class WindowStateManager {

    public static final String WINDOW_RESIZE_ACTION_NAME = "windowResize";
    public static final String WINDOW_RESIZE_KEY_NAME = "F11";

    public static final int STATE_WINDOW_FULL_SCREEN = 0;
    public static final int STATE_WINDOW_USER_MODIFIED = 1;

    private static final int DEFAULT_WINDOW_STATE = STATE_WINDOW_USER_MODIFIED;

    private final GameFrame frame;

    private int windowState;
    private boolean isListeningToKeyboardInput;

    public WindowStateManager(GameFrame gameFrame){
        this.frame = gameFrame;
        isListeningToKeyboardInput = true;
        windowState = -1;
        setWindowState(DEFAULT_WINDOW_STATE);
        GameKeyBindingAdapter windowResize = new GameKeyBindingAdapter(WINDOW_RESIZE_ACTION_NAME, WINDOW_RESIZE_KEY_NAME){
            @Override
            protected void onKeyPressed(){
                if(isListeningToKeyboardInput){
                    onWindowStateKeyPressed();
                }
            }
        };
        gameFrame.getActionManager().addGameKeyBinding(windowResize);
    }

    protected void onWindowStateKeyPressed(){
        if(windowState == STATE_WINDOW_FULL_SCREEN){
            setWindowState(STATE_WINDOW_USER_MODIFIED);
            return;
        }
        if(windowState == STATE_WINDOW_USER_MODIFIED){
            setWindowState(STATE_WINDOW_FULL_SCREEN);
            return;
        }
    }

    public boolean setWindowState(int newWindowState){
        if(windowState == newWindowState){
            return false;
        }
        if(frame == null){
            return false;
        }
        switch(newWindowState){
            case STATE_WINDOW_FULL_SCREEN: {
                setStateWindowFullScreen();
                break;
            }
            case STATE_WINDOW_USER_MODIFIED: {
                setStateWindowUserModified();
                break;
            }
            default:{
                setWindowState(DEFAULT_WINDOW_STATE);
                break;
            }
        }
        windowState = newWindowState;
        return true;
    }

    protected void setStateWindowFullScreen(){
        frame.dispose();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    protected void setStateWindowUserModified(){
        Toolkit tk = Toolkit.getDefaultToolkit();
        int screenWidth = (int) tk.getScreenSize().getWidth();
        int screenHeight = (int) tk.getScreenSize().getHeight();
        Dimension frameSize = new Dimension(screenWidth/2, screenHeight/2);
        frame.setMinimumSize(frameSize);
        frame.setSize(frameSize);
        frame.setLocation((screenWidth - frame.getWidth())/2, (screenHeight - frame.getHeight())/2);
        frame.dispose();
        frame.setUndecorated(false);
        frame.setVisible(true);
    }

    public void setListeningToKeyShortcuts(boolean shouldListenToKeyboardInput){
        this.isListeningToKeyboardInput = shouldListenToKeyboardInput;
    }

    public boolean isListeningToKeyboardInput(){
        return isListeningToKeyboardInput;
    }

    public int getWindowState(){
        return windowState;
    }
}
