package fsteel.userInputListening;

import javax.swing.*;
import java.awt.event.ActionEvent;


public abstract class GameKeyBinding {

    private final String actionName;
    private final KeyStroke keyPressedStroke;
    private final KeyStroke keyReleasedStroke;

    private final Action pressedAction;
    private final Action releasedAction;

    private boolean isKeyPressed;

    public GameKeyBinding(String actionName, String keyName){
        this.actionName = actionName;
        this.keyPressedStroke = KeyStroke.getKeyStroke("pressed " + keyName);
        this.keyReleasedStroke = KeyStroke.getKeyStroke("released " + keyName);
        this.pressedAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isKeyPressed){
                    onKeyPressed();
                }
                isKeyPressed = true;
            }
        };
        this.releasedAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isKeyPressed){
                    onKeyReleased();
                }
                isKeyPressed = false;
            }
        };
        isKeyPressed = false;
    }

    public KeyStroke getPressedKeyStroke(){
        return keyPressedStroke;
    }

    public KeyStroke getReleasedKeyStroke(){
        return keyReleasedStroke;
    }

    public String getActionName(){
        return actionName;
    }

    public Action getPressedAction(){
        return pressedAction;
    }

    public Action getReleasedAction(){ return releasedAction; }

    public boolean isKeyPressed(){
        return isKeyPressed;
    }

    protected abstract void onKeyPressed();
    protected abstract void onKeyReleased();


}
