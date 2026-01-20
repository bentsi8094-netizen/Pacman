package model.entity;

import model.CollisionChecker;
import model.Config;
import model.GameModel;
import java.awt.Point;

public abstract class Ghost extends Entity {
    public enum Behavior { SCATTER, CHASE, FRIGHTENED, EATEN }
    public Behavior currentBehavior = Behavior.SCATTER;
    public String name;
    public boolean isExiting = true;
    protected GameModel model;
    protected Point targetTile = new Point(0, 0);
    protected int behaviorTimer = 0;
    protected int cycleCount = 0;

    public Ghost(GameModel model) {
        this.model = model;
        this.speed = 1;
        this.direction = "up";
    }

    public void update(CollisionChecker checker) {
        updateBehaviorState();
        
        if (isExiting) {
            exitHouseLogic();
        } else {
            if (isAtIntersection()) {
                updateTargetTile();
                chooseBestDirection(checker);
            }
            collisionOn = false;
            checker.checkTile(this);
            if (!collisionOn) {
                move();
            } else {
                forceNewDirection(checker);
            }
        }
        updateAnimation();
    }

    private void updateBehaviorState() {
        if (currentBehavior == Behavior.FRIGHTENED || currentBehavior == Behavior.EATEN) return;

        behaviorTimer++;
        int[] schedule = {420, 1200, 420, 1200, 300, 1200, 300, -1};
        int currentLimit = schedule[cycleCount];

        if (currentLimit != -1 && behaviorTimer >= currentLimit) {
            behaviorTimer = 0;
            cycleCount++;
            currentBehavior = (currentBehavior == Behavior.SCATTER) ? Behavior.CHASE : Behavior.SCATTER;
            direction = getOppositeDirection(direction);
        }
    }

    protected abstract void updateTargetTile();

    protected void chooseBestDirection(CollisionChecker checker) {
        String[] dirs = {"up", "left", "down", "right"};
        String opposite = getOppositeDirection(direction);
        String bestDir = direction;
        double minDistance = Double.MAX_VALUE;

        x = (x / Config.TILE_SIZE) * Config.TILE_SIZE;
        y = (y / Config.TILE_SIZE) * Config.TILE_SIZE;

        for (String d : dirs) {
            if (d.equals(opposite)) continue;
            if (isRestrictedTile() && d.equals("up")) continue;

            if (!checker.isWall(this, d)) {
                double dist = calculateDistance(d);
                if (dist < minDistance) {
                    minDistance = dist;
                    bestDir = d;
                }
            }
        }
        direction = bestDir;
    }

    private double calculateDistance(String nextDir) {
        int nextX = x, nextY = y;
        if (nextDir.equals("up")) nextY -= Config.TILE_SIZE;
        else if (nextDir.equals("down")) nextY += Config.TILE_SIZE;
        else if (nextDir.equals("left")) nextX -= Config.TILE_SIZE;
        else if (nextDir.equals("right")) nextX += Config.TILE_SIZE;

        return Math.sqrt(Math.pow(nextX - targetTile.x, 2) + Math.pow(nextY - targetTile.y, 2));
    }

    private boolean isRestrictedTile() {
        int col = x / Config.TILE_SIZE;
        int row = y / Config.TILE_SIZE;
        return (row == 11 || row == 23) && (col >= 11 && col <= 16);
    }

    private void exitHouseLogic() {
        int targetX = 13 * Config.TILE_SIZE;
        int targetY = 11 * Config.TILE_SIZE;
        if (x < targetX) x++;
        else if (x > targetX) x--;
        if (x == targetX) {
            if (y > targetY) y--;
            else {
                isExiting = false;
                direction = "left";
            }
        }
    }

    protected boolean isAtIntersection() {
        return (x % Config.TILE_SIZE == 0 && y % Config.TILE_SIZE == 0);
    }

    protected String getOppositeDirection(String dir) {
        switch (dir) {
            case "up": return "down";
            case "down": return "up";
            case "left": return "right";
            case "right": return "left";
            default: return "";
        }
    }

    private void forceNewDirection(CollisionChecker checker) {
        String[] dirs = {"up", "left", "down", "right"};
        for (String d : dirs) {
            if (!checker.isWall(this, d)) {
                direction = d;
                x = (x / Config.TILE_SIZE) * Config.TILE_SIZE;
                y = (y / Config.TILE_SIZE) * Config.TILE_SIZE;
                break;
            }
        }
    }

    protected void move() {
        if (direction.equals("up")) y -= speed;
        else if (direction.equals("down")) y += speed;
        else if (direction.equals("left")) x -= speed;
        else if (direction.equals("right")) x += speed;
        
        if (x < -Config.TILE_SIZE) x = Config.MAP_WIDTH;
        if (x > Config.MAP_WIDTH) x = -Config.TILE_SIZE;
    }

    public void resetPosition() {
        this.x = 13 * Config.TILE_SIZE;
        this.y = 14 * Config.TILE_SIZE;
        this.isExiting = true;
        this.direction = "up";
    }

    protected void updateAnimation() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}