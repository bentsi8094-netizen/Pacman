package model.entity;

import model.CollisionChecker;
import model.Config;
import java.util.Random;

public abstract class Ghost extends Entity {
    public String name;
    protected int actionLockCounter = 0;
    private Random random = new Random();

    public Ghost() {
        speed = 1;
        direction = "up";
    }

    public void update(CollisionChecker checker) {
        checkCollision(checker);
        if (collisionOn) {
            updateDirection();
        }
        move();
        updateAnimation();
    }

    public void resetPosition() {
        this.x = 13 * Config.TILE_SIZE;
        this.y = 11 * Config.TILE_SIZE;
        this.direction = "up";
        this.collisionOn = false;
    }

    protected void checkCollision(CollisionChecker checker) {
        collisionOn = false;
        checker.checkTile(this);
    }

    protected void updateDirection() {
        actionLockCounter++;
        if (actionLockCounter >= 60 || collisionOn) {
            int i = random.nextInt(4);
            direction = (i == 0) ? "up" : (i == 1) ? "down" : (i == 2) ? "left" : "right";
            actionLockCounter = 0;
        }
    }

    private void move() {
        if (!collisionOn) {
            if (direction.equals("up")) y -= speed;
            if (direction.equals("down")) y += speed;
            if (direction.equals("left")) x -= speed;
            if (direction.equals("right")) x += speed;
        }
        handleTunnel();
    }

    private void handleTunnel() {
        int limit = Config.MAX_SCREEN_COLS * Config.TILE_SIZE;
        if (x < -Config.TILE_SIZE) x = limit;
        if (x > limit) x = -Config.TILE_SIZE;
    }

    private void updateAnimation() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}