package fsteel.window;

import fsteel.gameclock.rendering.ScreenObjectRenderer;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private final GamePane gamePane;
    private final JPanel backgroundPane;
    private final WindowStateManager stateManager;
    private final WindowActionManager actionManager;

    public GameFrame(ScreenObjectRenderer renderer){
        super("Felix tough as steel");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setIgnoreRepaint(true);
        gamePane = new GamePane(renderer);
        backgroundPane = new JPanel();
        backgroundPane.setLayout(new GridLayout(1,1));
        backgroundPane.add(gamePane);
        backgroundPane.setIgnoreRepaint(true);
        super.getRootPane().setContentPane(backgroundPane);
        actionManager = new WindowActionManager(backgroundPane);
        stateManager = new WindowStateManager(this);
        backgroundPane.setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, actionManager.getInputMap());
        backgroundPane.setActionMap(actionManager.getActionMap());
        gamePane.createBufferStrategy(GamePane.BUFFER_AMOUNT);
    }

    public WindowActionManager getActionManager(){
        return actionManager;
    }

    public WindowStateManager getStateManager(){
        return stateManager;
    }

    @Override
    public JPanel getContentPane(){
        return backgroundPane;
    }

    public GamePane getGamePane(){
        return gamePane;
    }
}
