package model.entity;

import model.CollisionChecker;
import model.Config;

public class Blinky extends Ghost {
    public Blinky() {
        super();
        this.name = "Blinky";
        // מיקום התחלתי בטוח בתוך הבית (משבצות 13, 14 במרכז)
        this.x = 13 * Config.TILE_SIZE;
        this.y = 14 * Config.TILE_SIZE;
        this.speed = 1;
        this.direction = "left";
    }

    @Override
    public void update(CollisionChecker checker) {
        super.update(checker);
    }
}