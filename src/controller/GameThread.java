package controller;

import view.GamePanel;
import model.GameModel;
import model.MoveCommand;

public class GameThread implements Runnable {
    private GamePanel panel;
    private GameModel gameModel;
    private MoveCommand command;
    private boolean running = true;

    public GameThread(GameModel gameModel, GamePanel panel, MoveCommand command) {
        this.gameModel = gameModel;
        this.panel = panel;
        this.command = command;
    }

    @Override
    public void run() {
        while (running) {
            gameModel.update(command);
            panel.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void stop() { running = false; }
}