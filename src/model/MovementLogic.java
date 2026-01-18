package model;

public class MovementLogic {
    private int playerX = 26;
    private int playerY = 26;
    private int speed = 4;

    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }

    public void update(MoveCommand command) {
        if (command.isUp()) playerY -= speed;
        if (command.isDown()) playerY += speed;
        if (command.isLeft()) playerX -= speed;
        if (command.isRight()) playerX += speed;
    }
}