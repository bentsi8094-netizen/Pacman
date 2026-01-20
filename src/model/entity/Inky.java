package model.entity;

import model.GameModel;
import model.Config;
import java.awt.Point;

public class Inky extends Ghost {
    public Inky(GameModel model) {
        super(model);
        this.name = "Inky";
        this.x = 11 * Config.TILE_SIZE;
        this.y = 14 * Config.TILE_SIZE;
    }

    @Override
    protected void updateTargetTile() {
        if (currentBehavior == Behavior.SCATTER) {
            targetTile = new Point(27 * Config.TILE_SIZE, 31 * Config.TILE_SIZE);
        } else {
            Player p = model.getPlayer();
            int pivotX = p.x;
            int pivotY = p.y;
            if (p.direction.equals("up")) pivotX -= 2 * Config.TILE_SIZE;
            else if (p.direction.equals("down")) pivotY += 2 * Config.TILE_SIZE;
            else if (p.direction.equals("left")) pivotX -= 2 * Config.TILE_SIZE;
            else if (p.direction.equals("right")) pivotX += 2 * Config.TILE_SIZE;

            Blinky blinky = (Blinky) model.getGhosts().get(0);
            int dx = pivotX - blinky.x;
            int dy = pivotY - blinky.y;
            targetTile = new Point(pivotX + dx, pivotY + dy);
        }
    }
}