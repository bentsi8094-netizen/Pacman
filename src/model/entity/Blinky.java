package model.entity;

import model.GameModel;
import model.Config;

public class Blinky extends Ghost {
    public Blinky(GameModel model) {
        super(model);
        this.name = "Blinky";
        this.x = 13 * Config.TILE_SIZE;
        this.y = 14 * Config.TILE_SIZE;
        this.direction = "up";
    }
}