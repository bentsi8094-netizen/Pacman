package model;

import model.entity.Player;
import view.TileManager;

public class GameModel {
    private Player player;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    private int score = 0;
    private int lives = 3;
    private double playTime = 0;
    private boolean isPowerMode = false;
    private int powerTimer = 0;

    public GameModel() {
        this.tileManager = new TileManager();
        this.player = new Player();
        this.collisionChecker = new CollisionChecker(this);
    }

    public void update(MoveCommand command) {
        player.update(command, collisionChecker);
        checkItemConsumption();
        
        if (isPowerMode) {
            powerTimer--;
            if (powerTimer <= 0) isPowerMode = false;
        }
        playTime += (double)1/60;
    }

    private void checkItemConsumption() {
        int col = (player.x + Config.TILE_SIZE / 2) / Config.TILE_SIZE;
        int row = (player.y + Config.TILE_SIZE / 2) / Config.TILE_SIZE;

        if (col >= 0 && col < Config.MAX_SCREEN_COLS && row >= 0 && row < Config.MAX_SCREEN_ROWS) {
            int tile = tileManager.mapTileNum[col][row];
            if (tile == 0 || tile == 3) {
                tileManager.mapTileNum[col][row] = 5; // הופך לשביל ריק
                score += (tile == 0) ? 10 : 50;
                if (tile == 3) activatePowerMode();
            }
        }
    }

    private void activatePowerMode() {
        isPowerMode = true;
        powerTimer = 600;
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public double getPlayTime() { return playTime; }
    public boolean isPowerMode() { return isPowerMode; }
    public Player getPlayer() { return player; }
    public TileManager getTileManager() { return tileManager; }
}