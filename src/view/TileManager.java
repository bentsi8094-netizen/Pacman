package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.Config;

public class TileManager {
    public int[][] mapTileNum;

    public TileManager() {
        mapTileNum = new int[Config.MAX_SCREEN_COLS][Config.MAX_SCREEN_ROWS];
        // נשתמש בנתיב המדויק שציינת (נניח ששינית ל-txt)
        loadMap("src/image/map/map01.txt"); 
    }

    public void loadMap(String filePath) {
        File file = new File(filePath);
        
        // בדיקה אם הקובץ בכלל קיים בנתיב הזה
        if (!file.exists()) {
            System.out.println("טעות בנתיב! הקוד מחפש ב: " + file.getAbsolutePath());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            int row = 0;
            while (row < Config.MAX_SCREEN_ROWS) {
                String line = br.readLine();
                if (line == null) break;

                // פיצול לפי רווחים כדי לתמוך בפורמט שלך
                String[] numbers = line.trim().split("\\s+");

                for (int col = 0; col < Config.MAX_SCREEN_COLS && col < numbers.length; col++) {
                    mapTileNum[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
            System.out.println("המפה נטענה בהצלחה מ: " + filePath);
        } catch (IOException | NumberFormatException e) {
            System.out.println("שגיאה בקריאת הקובץ: " + e.getMessage());
        }
    }
}