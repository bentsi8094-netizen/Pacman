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

        g2.setColor(new Color(30, 20, 150));
        g2.fillRect(xStart, 0, Config.INFO_WIDTH, Config.SCREEN_HEIGHT);

        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(xStart, 0, xStart, Config.SCREEN_HEIGHT);

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2.setFont(arial_28);
        g2.setColor(Color.BLACK);
        g2.drawString("SCORE", xStart + 40, 80);

        g2.setFont(arial_40);
        g2.setColor(Color.YELLOW);
        g2.drawString(String.format("%05d", gameModel.getScore()), xStart + 40, 130);

        g2.setFont(arial_28);
        g2.setColor(Color.BLACK);
        g2.drawString("TIME", xStart + 40, 220);

        g2.setFont(arial_40);
        g2.setColor(new Color(0, 255, 100));
        g2.drawString(dFormat.format(gameModel.getPlayTime()) + "s", xStart + 40, 270);

        g2.setFont(arial_28);
        g2.setColor(Color.BLACK);
        g2.drawString("LIVES", xStart + 40, 360);

        g2.setColor(Color.YELLOW);
        for (int i = 0; i < gameModel.getLives(); i++) {
            g2.fillArc(xStart + 40 + (i * 40), 380, 30, 30, 45, 270);
        }

        if (gameModel.isPowerMode()) {
            g2.setFont(arial_28);
            g2.setColor(Color.RED);
            g2.drawString("POWER!", xStart + 40, 480);
        }
    }
}