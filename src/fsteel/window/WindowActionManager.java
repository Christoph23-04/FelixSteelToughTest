package fsteel.window;

import fsteel.userInputListening.GameKeyBinding;

import javax.swing.*;

public class WindowActionManager {

    private static final String KEY_RELEASED_ACTION_EXTENSION = "_released";

    private final ComponentInputMap inputM;
    private final ActionMap actionM;

    public WindowActionManager(JComponent component){
       this.inputM = new ComponentInputMap(component);
       this.actionM = new ActionMap();
    }

    public void addGameKeyBinding(GameKeyBinding gkb){
        inputM.put(gkb.getPressedKeyStroke(), gkb.getActionName());
        inputM.put(gkb.getReleasedKeyStroke(), gkb.getActionName() + KEY_RELEASED_ACTION_EXTENSION);
        actionM.put(gkb.getActionName(), gkb.getPressedAction());
        actionM.put(gkb.getActionName() + KEY_RELEASED_ACTION_EXTENSION, gkb.getReleasedAction());
    }

    public void removeGameKeyBinding(GameKeyBinding gbk) {
        inputM.remove(gbk.getPressedKeyStroke());
        inputM.remove(gbk.getReleasedKeyStroke());
        actionM.remove(gbk.getActionName());
        actionM.remove(gbk.getActionName() + KEY_RELEASED_ACTION_EXTENSION);
    }

    public ComponentInputMap getInputMap(){
        return inputM;
    }

    public ActionMap getActionMap(){
        return actionM;
    }
}
