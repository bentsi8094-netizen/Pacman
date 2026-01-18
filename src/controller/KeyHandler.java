package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.MoveCommand;

public class KeyHandler implements KeyListener, MoveCommand {
    private boolean up, down, left, right;

    @Override public boolean isUp() { return up; }
    @Override public boolean isDown() { return down; }
    @Override public boolean isLeft() { return left; }
    @Override public boolean isRight() { return right; }

    @Override
    public void keyPressed(KeyEvent e) {
        toggle(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggle(e.getKeyCode(), false);
    }

    private void toggle(int code, boolean pressed) {
        if (code == KeyEvent.VK_UP) up = pressed;
        if (code == KeyEvent.VK_DOWN) down = pressed;
        if (code == KeyEvent.VK_LEFT) left = pressed;
        if (code == KeyEvent.VK_RIGHT) right = pressed;
    }

    @Override public void keyTyped(KeyEvent e) {}
}