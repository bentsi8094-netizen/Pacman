package model.entity;

import model.GameModel;
import model.Config;
import java.awt.Point;

public class Pinky extends Ghost {
    public Pinky(GameModel model) {
        super(model);
        this.name = "Pinky";
        this.x = 13 * Config.TILE_SIZE;
        this.y = 14 * Config.TILE_SIZE;
    }

    @Override
    protected void updateTargetTile() {
        if (currentBehavior == Behavior.SCATTER) {
            targetTile = new Point(2 * Config.TILE_SIZE, -2 * Config.TILE_SIZE);
        } else {
            Player p = model.getPlayer();
            int tx = p.x;
            int ty = p.y;
            switch (p.direction) {
                case "up": tx -= 4 * Config.TILE_SIZE; ty -= 4 * Config.TILE_SIZE; break;
                case "down": ty += 4 * Config.TILE_SIZE; break;
                case "left": tx -= 4 * Config.TILE_SIZE; break;
                case "right": tx += 4 * Config.TILE_SIZE; break;
            }
            targetTile = new Point(tx, ty);
        }
    }
}