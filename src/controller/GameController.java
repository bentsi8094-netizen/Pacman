package controller;

import model.GameModel;
import model.MoveCommand;
import view.GamePanel;

public class GameController implements Runnable {
    private GameModel model;
    private GamePanel view;
    private KeyHandler keyHandler;
    private Thread gameThread;
    private final int FPS = 60;

    public GameController(GameModel model, GamePanel view, KeyHandler keyHandler) {
        this.model = model;
        this.view = view;
        this.keyHandler = keyHandler;
    }

    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                view.repaint();
                delta--;
            }
        }
    }

    private void update() {
        MoveCommand command = keyHandler.getCommand();
        model.update(command);
    }
}