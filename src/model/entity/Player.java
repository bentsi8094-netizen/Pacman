package model.entity;

import model.CollisionChecker;
import model.MoveCommand;
import model.Config;

public class Player extends Entity {
    
    public Player() {
        x = Config.TILE_SIZE * 13; 
        y = Config.TILE_SIZE * 23;
        speed = 2;
        direction = "right";
    }

    public void update(MoveCommand command, CollisionChecker checker) {
        if (command != null) {
            String requestedDir = command.getDirection();
            
            // ניסיון פנייה
            if (!requestedDir.equals(direction)) {
                if (!checker.isWall(this, requestedDir)) {
                    // יישור מיידי למרכז המשבצת כדי לאפשר תנועה חלקה בשביל החדש
                    if (requestedDir.equals("up") || requestedDir.equals("down")) {
                        x = ((x + Config.TILE_SIZE / 2) / Config.TILE_SIZE) * Config.TILE_SIZE;
                    } else {
                        y = ((y + Config.TILE_SIZE / 2) / Config.TILE_SIZE) * Config.TILE_SIZE;
                    }
                    this.direction = requestedDir;
                }
            }
        }

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
        
        handleTunnel();
        updateAnimation();
    }

    private void handleTunnel() {
        if (x < -Config.TILE_SIZE) x = Config.MAP_WIDTH;
        if (x > Config.MAP_WIDTH) x = -Config.TILE_SIZE;
    }

    private void updateAnimation() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}