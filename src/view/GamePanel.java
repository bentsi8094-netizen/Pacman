package view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import model.GameModel;
import model.Config;
import model.entity.Player;

public class GamePanel extends JPanel {
    private GameModel gameModel;
    private UI ui;
    private BufferedImage wallImg, grassImg, ironImg;
    private BufferedImage upOpen, upClose, downOpen, downClose, leftOpen, leftClose, rightOpen, rightClose;

    public GamePanel(GameModel gameModel) {
        this.gameModel = gameModel;
        this.ui = new UI(gameModel);
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        loadImages();
    }

    private void loadImages() {
        try {
            upOpen = ImageIO.read(new File("src/image/openUp.png"));
            upClose = ImageIO.read(new File("src/image/closeUp.png"));
            downOpen = ImageIO.read(new File("src/image/openDown.png"));
            downClose = ImageIO.read(new File("src/image/closeDown.png"));
            leftOpen = ImageIO.read(new File("src/image/openLeft.png"));
            leftClose = ImageIO.read(new File("src/image/closeLeft.png"));
            rightOpen = ImageIO.read(new File("src/image/openRight.png"));
            rightClose = ImageIO.read(new File("src/image/closeRight.png"));
            wallImg = ImageIO.read(new File("src/image/woll.png"));
            grassImg = ImageIO.read(new File("src/image/grass.png"));
            ironImg = ImageIO.read(new File("src/image/airon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawMap(g2);
        drawPlayer(g2);
        ui.draw(g2);
        g2.dispose();
    }

    private void drawMap(Graphics2D g2) {
        int[][] map = gameModel.getTileManager().mapTileNum;
        for (int row = 0; row < Config.MAX_SCREEN_ROWS; row++) {
            for (int col = 0; col < Config.MAX_SCREEN_COLS; col++) {
                int tileType = map[col][row];
                int x = col * Config.TILE_SIZE;
                int y = row * Config.TILE_SIZE;

                if (tileType == 0 || tileType == 5 || tileType == 3) {
                    g2.drawImage(grassImg, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                    if (tileType == 0) {
                        g2.setColor(Color.WHITE);
                        g2.fillOval(x + 10, y + 10, 4, 4);
                    } else if (tileType == 3) {
                        g2.setColor(Color.WHITE);
                        g2.fillOval(x + 6, y + 6, 12, 12);
                    }
                } else if (tileType == 1) {
                    g2.drawImage(wallImg, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                } else if (tileType == 2) {
                    g2.drawImage(ironImg, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                } else if (tileType == 4) {
                    g2.drawImage(grassImg, x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                    g2.setColor(new Color(255, 182, 255));
                    g2.fillRect(x, y + 12, Config.TILE_SIZE, 4);
                }
            }
        }
    }

    private void drawPlayer(Graphics2D g2) {
        Player p = gameModel.getPlayer();
        BufferedImage image = null;
        switch (p.direction) {
            case "up": image = (p.spriteNum == 1) ? upOpen : upClose; break;
            case "down": image = (p.spriteNum == 1) ? downOpen : downClose; break;
            case "left": image = (p.spriteNum == 1) ? leftOpen : leftClose; break;
            case "right": image = (p.spriteNum == 1) ? rightOpen : rightClose; break;
        }
        if (image != null) g2.drawImage(image, p.x, p.y, Config.TILE_SIZE, Config.TILE_SIZE, null);
    }
}