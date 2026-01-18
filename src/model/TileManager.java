package model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public int[][] mapTileNum;

    public TileManager() {
        mapTileNum = new int[Config.MAX_SCREEN_COLS][Config.MAX_SCREEN_ROWS];
        loadMap("/image/map/map01.txt");
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (row < Config.MAX_SCREEN_ROWS) {
                String line = br.readLine();
                if (line == null) break;
                String numbers[] = line.split(" ");
                for (col = 0; col < Config.MAX_SCREEN_COLS; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}