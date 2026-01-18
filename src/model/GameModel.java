package model;

import model.entity.Player;
import model.entity.Ghost;
import model.entity.Blinky;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    public enum GameState { MENU, PLAYING, PAUSED, GAME_OVER }
    private GameState currentState = GameState.PLAYING;

    private Player player;
    private List<Ghost> ghosts;
    private TileManager tileManager;
    private CollisionChecker collisionChecker;
    
    private int score = 0;
    private int lives = 3;
    private double playTime = 0;
    private boolean isPowerMode = false;
    private int powerTimer = 0;
    private int startDelay = 60; 

    public GameModel() {
        this.tileManager = new TileManager();
        this.collisionChecker = new CollisionChecker(this);
        initLevel();
    }

    public void initLevel() {
        // חשוב ליצור קודם את השחקן כדי שהרוחות יוכלו למצוא אותו ב-update הראשון
        this.player = new Player();
        this.ghosts = new ArrayList<>();
        
        // כאן התיקון: מעבירים את המודל (this) לבנאי של בלינקי
        this.ghosts.add(new Blinky(this));
        
        this.startDelay = 60; 
    }

    public void update(MoveCommand command) {
        if (currentState != GameState.PLAYING) return;

        if (startDelay > 0) {
            startDelay--;
            return;
        }

        player.update(command, collisionChecker);
        
        for (Ghost ghost : ghosts) {
            ghost.update(collisionChecker);
            checkCollisionWithGhost(ghost);
        }
        
        checkItemConsumption();
        
        if (isPowerMode) {
            powerTimer--;
            if (powerTimer <= 0) isPowerMode = false;
        }
        
        playTime += (1.0 / 60.0);
    }

    private void checkCollisionWithGhost(Ghost ghost) {
        // חישוב מרחק ממרכז למרכז
        int dx = Math.abs((player.x + 12) - (ghost.x + 12));
        int dy = Math.abs((player.y + 12) - (ghost.y + 12));

        if (dx < 10 && dy < 10) {
            if (isPowerMode) {
                ghost.resetPosition();
                score += 200;
            } else {
                handlePlayerDeath();
            }
        }
    }

    private void handlePlayerDeath() {
        lives--;
        if (lives <= 0) currentState = GameState.GAME_OVER;
        else initLevel();
    }

    private void checkItemConsumption() {
        int col = (player.x + 12) / Config.TILE_SIZE;
        int row = (player.y + 12) / Config.TILE_SIZE;
        int tile = tileManager.getTileAt(col, row);
        if (tile == 0 || tile == 3) {
            tileManager.setTile(col, row, 5); 
            score += (tile == 0) ? 10 : 50;
            if (tile == 3) {
                isPowerMode = true;
                powerTimer = 600;
            }
        }
    }

    public GameState getState() { return currentState; }
    public void setState(GameState state) { this.currentState = state; }
    public Player getPlayer() { return player; }
    public List<Ghost> getGhosts() { return ghosts; }
    public TileManager getTileManager() { return tileManager; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public double getPlayTime() { return playTime; }
    public boolean isPowerMode() { return isPowerMode; }
}