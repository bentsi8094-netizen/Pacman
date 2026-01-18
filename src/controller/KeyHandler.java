package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.MoveCommand;

public class KeyHandler implements KeyListener, MoveCommand {
    private boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public boolean isUp() { return upPressed; }
    @Override
    public boolean isDown() { return downPressed; }
    @Override
    public boolean isLeft() { return leftPressed; }
    @Override
    public boolean isRight() { return rightPressed; }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) upPressed = true;
        if (code == KeyEvent.VK_DOWN) downPressed = true;
        if (code == KeyEvent.VK_LEFT) leftPressed = true;
        if (code == KeyEvent.VK_RIGHT) rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) upPressed = false;
        if (code == KeyEvent.VK_DOWN) downPressed = false;
        if (code == KeyEvent.VK_LEFT) leftPressed = false;
        if (code == KeyEvent.VK_RIGHT) rightPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}