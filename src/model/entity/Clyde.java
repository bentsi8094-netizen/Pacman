package model.entity;

import model.GameModel;
import model.Config;
import java.awt.Point;

public class Clyde extends Ghost {
    public Clyde(GameModel model) {
        super(model);
        this.name = "Clyde";
        this.x = 15 * Config.TILE_SIZE;
        this.y = 14 * Config.TILE_SIZE;
    }

    @Override
    protected void updateTargetTile() {
        double distToPlayer = Math.sqrt(Math.pow(x - model.getPlayer().x, 2) + Math.pow(y - model.getPlayer().y, 2));
        if (currentBehavior == Behavior.SCATTER || distToPlayer < 8 * Config.TILE_SIZE) {
            targetTile = new Point(0, 31 * Config.TILE_SIZE);
        } else {
            targetTile = new Point(model.getPlayer().x, model.getPlayer().y);
        }
    }
}