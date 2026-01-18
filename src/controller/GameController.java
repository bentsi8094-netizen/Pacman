package controller;

import model.GameModel;
import view.GamePanel;

public class GameController {
    private GameThread gameThread;
    private KeyHandler keyHandler;

    public GameController(GameModel model, GamePanel panel) {
        this.keyHandler = new KeyHandler();
        panel.addKeyListener(keyHandler);
        panel.setFocusable(true);

        // ה-KeyHandler הוא ה-Command שמחזיק את מצב המקלדת
        this.gameThread = new GameThread(model, panel, keyHandler);
        startGame();
    }

    private void startGame() {
        Thread thread = new Thread(gameThread);
        thread.start();
    }
}