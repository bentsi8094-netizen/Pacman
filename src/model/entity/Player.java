package model.entity;

import model.CollisionChecker;
import model.Config;
import model.MoveCommand;

public class Player extends Entity {
    public Player() {
        x = 324; y = 552; speed = 2; direction = "right";
    }

    public void update(MoveCommand command, CollisionChecker checker) {
        if (command.isUp()) direction = "up";
        else if (command.isDown()) direction = "down";
        else if (command.isLeft()) direction = "left";
        else if (command.isRight()) direction = "right";

        collisionOn = false;
        checker.checkTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }

        // לוגיקת המנהרה (Teleport)
        int screenLimit = Config.MAX_SCREEN_COLS * Config.TILE_SIZE;
        if (x < -Config.TILE_SIZE) x = screenLimit;
        else if (x > screenLimit) x = -Config.TILE_SIZE;

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}