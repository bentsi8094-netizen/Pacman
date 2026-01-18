package view;

import java.awt.*;
import java.text.DecimalFormat;
import model.Config;
import model.GameModel;

public class UI {
    private GameModel gameModel;
    private Font arial_28, arial_40;
    private DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GameModel gameModel) {
        this.gameModel = gameModel;
        this.arial_28 = new Font("Arial", Font.BOLD, 28);
        this.arial_40 = new Font("Arial", Font.BOLD, 40);
    }

    public void draw(Graphics2D g2) {
        int xStart = Config.MAP_WIDTH;

        // Sidebar Background
        g2.setColor(new Color(30, 20, 170));
        g2.fillRect(xStart, 0, Config.INFO_WIDTH, Config.SCREEN_HEIGHT);

        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(xStart, 0, xStart, Config.SCREEN_HEIGHT);

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Score
        g2.setFont(arial_28);
        g2.setColor(Color.BLACK);
        g2.drawString("SCORE", xStart + 40, 80);
        g2.setFont(arial_40);
        g2.setColor(Color.YELLOW);
        g2.drawString(String.format("%05d", gameModel.getScore()), xStart + 40, 130);

        // Time
        g2.setFont(arial_28);
        g2.setColor(Color.BLACK);
        g2.drawString("TIME", xStart + 40, 220);
        g2.setFont(arial_40);
        g2.setColor(new Color(0, 255, 100));
        g2.drawString(dFormat.format(gameModel.getPlayTime()) + "s", xStart + 40, 270);

        // Lives
        g2.setFont(arial_28);
        g2.setColor(Color.BLACK);
        g2.drawString("LIVES", xStart + 40, 360);
        g2.setColor(Color.YELLOW);
        for (int i = 0; i < gameModel.getLives(); i++) {
            g2.fillArc(xStart + 40 + (i * 40), 380, 30, 30, 45, 270);
        }

        // Power Mode
        if (gameModel.isPowerMode()) {
            g2.setFont(arial_28);
            g2.setColor(Color.RED);
            g2.drawString("POWER!", xStart + 40, 480);
        }

        // Game State Messages
        if (gameModel.getState() == GameModel.GameState.GAME_OVER) {
            drawCenteredText(g2, "GAME OVER", Color.RED, 60);
        } else if (gameModel.getState() == GameModel.GameState.PAUSED) {
            drawCenteredText(g2, "PAUSED", Color.WHITE, 60);
        }
    }

    private void drawCenteredText(Graphics2D g2, String text, Color color, int size) {
        g2.setFont(new Font("Arial", Font.BOLD, size));
        g2.setColor(color);
        FontMetrics fm = g2.getFontMetrics();
        int x = (Config.MAP_WIDTH - fm.stringWidth(text)) / 2;
        int y = Config.MAP_HEIGHT / 2;
        g2.drawString(text, x, y);
    }
}