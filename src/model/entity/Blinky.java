package model.entity;

import model.GameModel;
import model.Config;
import java.awt.Point;

public class Blinky extends Ghost {
    public Blinky(GameModel model) {
        super(model);
        this.name = "Blinky";
        this.x = 13 * Config.TILE_SIZE;
        this.y = 11 * Config.TILE_SIZE;
        this.isExiting = false;
    }

    @Override
    protected void updateTargetTile() {
        if (currentBehavior == Behavior.SCATTER) {
            targetTile = new Point(25 * Config.TILE_SIZE, -2 * Config.TILE_SIZE);
        } else {
            targetTile = new Point(model.getPlayer().x, model.getPlayer().y);
        }
    }
}