package model;

import model.entity.Entity;

public class CollisionChecker {
    private GameModel model;

    public CollisionChecker(GameModel model) {
        this.model = model;
    }

    public void checkTile(Entity entity) {
        int padding = 8; 
        int entityLeftX = entity.x + padding;
        int entityRightX = entity.x + Config.TILE_SIZE - padding - 1;
        int entityTopY = entity.y + padding;
        int entityBottomY = entity.y + Config.TILE_SIZE - padding - 1;

        int leftCol = entityLeftX / Config.TILE_SIZE;
        int rightCol = entityRightX / Config.TILE_SIZE;
        int topRow = entityTopY / Config.TILE_SIZE;
        int bottomRow = entityBottomY / Config.TILE_SIZE;

        int t1, t2;

        switch (entity.direction) {
            case "up":
                t1 = model.getTileManager().getTileAt(leftCol, (entityTopY - entity.speed) / Config.TILE_SIZE);
                t2 = model.getTileManager().getTileAt(rightCol, (entityTopY - entity.speed) / Config.TILE_SIZE);
                if (isSolid(t1) || isSolid(t2)) entity.collisionOn = true;
                break;
            case "down":
                t1 = model.getTileManager().getTileAt(leftCol, (entityBottomY + entity.speed) / Config.TILE_SIZE);
                t2 = model.getTileManager().getTileAt(rightCol, (entityBottomY + entity.speed) / Config.TILE_SIZE);
                if (isSolid(t1) || isSolid(t2)) entity.collisionOn = true;
                break;
            case "left":
                t1 = model.getTileManager().getTileAt((entityLeftX - entity.speed) / Config.TILE_SIZE, topRow);
                t2 = model.getTileManager().getTileAt((entityLeftX - entity.speed) / Config.TILE_SIZE, bottomRow);
                if (isSolid(t1) || isSolid(t2)) entity.collisionOn = true;
                break;
            case "right":
                t1 = model.getTileManager().getTileAt((entityRightX + entity.speed) / Config.TILE_SIZE, topRow);
                t2 = model.getTileManager().getTileAt((entityRightX + entity.speed) / Config.TILE_SIZE, bottomRow);
                if (isSolid(t1) || isSolid(t2)) entity.collisionOn = true;
                break;
        }
    }

    private boolean isSolid(int tile) {
        return tile == 1 || tile == 2 || tile == 4;
    }

    public boolean isWall(Entity entity, String dir) {
        int nextX = entity.x;
        int nextY = entity.y;
        
        if (dir.equals("up")) nextY -= Config.TILE_SIZE;
        else if (dir.equals("down")) nextY += Config.TILE_SIZE;
        else if (dir.equals("left")) nextX -= Config.TILE_SIZE;
        else if (dir.equals("right")) nextX += Config.TILE_SIZE;

        int col = (nextX + Config.TILE_SIZE / 2) / Config.TILE_SIZE;
        int row = (nextY + Config.TILE_SIZE / 2) / Config.TILE_SIZE;
        
        int tile = model.getTileManager().getTileAt(col, row);
        return isSolid(tile);
    }
}