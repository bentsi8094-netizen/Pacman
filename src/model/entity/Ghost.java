package model.entity;

import model.CollisionChecker;
import model.Config;
import model.GameModel;

public abstract class Ghost extends Entity {
    public String name;
    public boolean isExiting = true;
    protected GameModel model;

    public Ghost(GameModel model) {
        this.model = model;
        this.speed = 1;
        this.direction = "up";
    }

    public void update(CollisionChecker checker) {
        if (isExiting) {
            exitHouseLogic();
        } else {
            // הרוח בודקת פניות רק כשהיא מגיעה למרכז משבצת (עם טולרנס קטן של המהירות)
            if (isAtIntersection()) {
                chooseBestDirection(checker);
            }
            
            collisionOn = false;
            checker.checkTile(this);
            
            if (!collisionOn) {
                move();
            } else {
                // אם הרוח נתקעה בכל זאת, היא מנסה לבחור כיוון חדש מיד
                forceNewDirection(checker);
            }
        }
        updateAnimation();
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
        // בדיקה אם הרוח נמצאת בטווח של מהירות אחת ממרכז המשבצת
        return (x % Config.TILE_SIZE < speed && y % Config.TILE_SIZE < speed);
    }

    protected void chooseBestDirection(CollisionChecker checker) {
        String[] dirs = {"up", "down", "left", "right"};
        String opposite = getOppositeDirection(direction);
        String bestDir = direction;
        double minDistance = Double.MAX_VALUE;

        // ברגע שהחלטנו לפנות - אנחנו מיישרים את הרוח למרכז המשבצת הנוכחית
        // זה מונע ממנה להיתקע בפינות בזמן הפנייה
        x = (x / Config.TILE_SIZE) * Config.TILE_SIZE;
        y = (y / Config.TILE_SIZE) * Config.TILE_SIZE;

        for (String d : dirs) {
            if (d.equals(opposite)) continue; // חוק הברזל: לא חוזרים אחורה

            if (!checker.isWall(this, d)) {
                double dist = getDistanceToPlayer(d);
                if (dist < minDistance) {
                    minDistance = dist;
                    bestDir = d;
                }
            }
        }
        direction = bestDir;
    }

    private void forceNewDirection(CollisionChecker checker) {
        String[] dirs = {"up", "down", "left", "right"};
        for (String d : dirs) {
            if (!checker.isWall(this, d)) {
                direction = d;
                x = (x / Config.TILE_SIZE) * Config.TILE_SIZE;
                y = (y / Config.TILE_SIZE) * Config.TILE_SIZE;
                break;
            }
        }
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

    protected double getDistanceToPlayer(String nextDir) {
        int nextX = x, nextY = y;
        if (nextDir.equals("up")) nextY -= Config.TILE_SIZE;
        else if (nextDir.equals("down")) nextY += Config.TILE_SIZE;
        else if (nextDir.equals("left")) nextX -= Config.TILE_SIZE;
        else if (nextDir.equals("right")) nextX += Config.TILE_SIZE;

        Player p = model.getPlayer();
        return Math.sqrt(Math.pow(nextX - p.x, 2) + Math.pow(nextY - p.y, 2));
    }

    protected void move() {
        if (direction.equals("up")) y -= speed;
        else if (direction.equals("down")) y += speed;
        else if (direction.equals("left")) x -= speed;
        else if (direction.equals("right")) x += speed;
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