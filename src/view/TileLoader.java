package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.GameModel;

public class TileLoader {
    public static void loadMap(GameModel model, String filePath) {
        File file = new File(filePath);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int row = 0;
            String line;
            while ((line = br.readLine()) != null && row < 31) {
                String[] numbers = line.trim().split("\\s+");
                for (int col = 0; col < numbers.length && col < 28; col++) {
                    int tileType = Integer.parseInt(numbers[col]);
                    model.getTileManager().setTile(col, row, tileType);
                }
                row++;
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Critical Error: Map loading failed: " + e.getMessage());
        }
    }
}