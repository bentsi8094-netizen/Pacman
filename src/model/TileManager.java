package model;

public class TileManager {
    public int[][] mapTileNum;

    public TileManager() {
        mapTileNum = new int[Config.MAX_SCREEN_COLS][Config.MAX_SCREEN_ROWS];
    }

    public void setTile(int col, int row, int value) {
        if (col >= 0 && col < Config.MAX_SCREEN_COLS && row >= 0 && row < Config.MAX_SCREEN_ROWS) {
            mapTileNum[col][row] = value;
        }
    }

    public int getTileAt(int col, int row) {
        if (col >= 0 && col < Config.MAX_SCREEN_COLS && row >= 0 && row < Config.MAX_SCREEN_ROWS) {
            return mapTileNum[col][row];
        }
        return -1; 
    }
}