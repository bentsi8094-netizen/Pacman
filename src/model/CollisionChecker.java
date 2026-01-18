package model;

import model.entity.Entity;

public class CollisionChecker {
    private GameModel model;

    public CollisionChecker(GameModel model) {
        this.model = model;
    }

    public void checkTile(Entity entity) {
        int entityLeftX = entity.x + entity.solidArea.x;
        int entityRightX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopY = entity.y + entity.solidArea.y;
        int entityBottomY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int leftCol = entityLeftX / Config.TILE_SIZE;
        int rightCol = entityRightX / Config.TILE_SIZE;
        int topRow = entityTopY / Config.TILE_SIZE;
        int bottomRow = entityBottomY / Config.TILE_SIZE;

        // לוגיקת מעבר בטוח: אם מחוץ לגבולות המפה, אין בדיקת התנגשות
        if (leftCol < 0 || rightCol >= Config.MAX_SCREEN_COLS || 
            topRow < 0 || bottomRow >= Config.MAX_SCREEN_ROWS) {
            entity.collisionOn = false;
            return;
        }

        int tile1, tile2;
        switch (entity.direction) {
            case "up":
                topRow = (entityTopY - entity.speed) / Config.TILE_SIZE;
                if (topRow >= 0) {
                    tile1 = model.getTileManager().mapTileNum[leftCol][topRow];
                    tile2 = model.getTileManager().mapTileNum[rightCol][topRow];
                    if (isSolid(tile1) || isSolid(tile2)) entity.collisionOn = true;
                }
                break;
            case "down":
                bottomRow = (entityBottomY + entity.speed) / Config.TILE_SIZE;
                if (bottomRow < Config.MAX_SCREEN_ROWS) {
                    tile1 = model.getTileManager().mapTileNum[leftCol][bottomRow];
                    tile2 = model.getTileManager().mapTileNum[rightCol][bottomRow];
                    if (isSolid(tile1) || isSolid(tile2)) entity.collisionOn = true;
                }
                break;
            case "left":
                leftCol = (entityLeftX - entity.speed) / Config.TILE_SIZE;
                if (leftCol >= 0) {
                    tile1 = model.getTileManager().mapTileNum[leftCol][topRow];
                    tile2 = model.getTileManager().mapTileNum[leftCol][bottomRow];
                    if (isSolid(tile1) || isSolid(tile2)) entity.collisionOn = true;
                }
                break;
            case "right":
                rightCol = (entityRightX + entity.speed) / Config.TILE_SIZE;
                if (rightCol < Config.MAX_SCREEN_COLS) {
                    tile1 = model.getTileManager().mapTileNum[rightCol][topRow];
                    tile2 = model.getTileManager().mapTileNum[rightCol][bottomRow];
                    if (isSolid(tile1) || isSolid(tile2)) entity.collisionOn = true;
                }
                break;
        }
    }

    private boolean isSolid(int tileType) {
        // 1=קיר, 2=ברזל, 4=שער
        return tileType == 1 || tileType == 2 || tileType == 4;
    }
}