package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.GameModel;
import model.MoveCommand;

public class KeyHandler implements KeyListener {
    private GameModel gameModel;
    private MoveCommand lastCommand;
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public MoveCommand getCommand() {
        return lastCommand;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
            lastCommand = new MoveCommand("up");
        }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
            lastCommand = new MoveCommand("down");
        }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
            lastCommand = new MoveCommand("left");
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
            lastCommand = new MoveCommand("right");
        }
        
        if (code == KeyEvent.VK_P) {
            if (gameModel.getState() == GameModel.GameState.PLAYING) {
                gameModel.setState(GameModel.GameState.PAUSED);
            } else if (gameModel.getState() == GameModel.GameState.PAUSED) {
                gameModel.setState(GameModel.GameState.PLAYING);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) upPressed = false;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) downPressed = false;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) leftPressed = false;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}