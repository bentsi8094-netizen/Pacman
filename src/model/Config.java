package model;

public class Config {
    public static final int TILE_SIZE = 24; 
    public static final int MAX_SCREEN_COLS = 28; 
    public static final int MAX_SCREEN_ROWS = 31; 
    
    public static final int MAP_WIDTH = TILE_SIZE * MAX_SCREEN_COLS;
    public static final int MAP_HEIGHT = TILE_SIZE * MAX_SCREEN_ROWS;

    public static final int INFO_WIDTH = 250; 
    public static final int SCREEN_WIDTH = MAP_WIDTH + INFO_WIDTH;
    public static final int SCREEN_HEIGHT = MAP_HEIGHT;
}