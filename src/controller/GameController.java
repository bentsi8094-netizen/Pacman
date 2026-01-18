package controller;

import model.GameModel;
import view.GamePanel;

public class GameController {
    private KeyHandler keyHandler;
    private GameThread gameThread;

    public GameController(GameModel model, GamePanel panel) {
        this.keyHandler = new KeyHandler();
        
        this.gameThread = new GameThread(model, panel, keyHandler);
        
        panel.addKeyListener(this.keyHandler);
        panel.setFocusable(true);
    }

    public void startGame() {
        Thread t = new Thread(gameThread);
        t.start();
    }
}