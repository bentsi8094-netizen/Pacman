package view;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import model.GameModel;
import model.Config;
import model.entity.Player;
import model.entity.Ghost;
import controller.KeyHandler;

public class GamePanel extends JPanel {
    private GameModel gameModel;
    private UI ui;
    private KeyHandler keyH;

    public GamePanel(GameModel gameModel, KeyHandler keyH) {
        this.gameModel = gameModel;
        this.keyH = keyH;
        this.ui = new UI(gameModel);
        
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        drawMap(g2);
        drawGhosts(g2);
        drawPlayer(g2);
        ui.draw(g2);
        
        Toolkit.getDefaultToolkit().sync();
        g2.dispose();
    }

    private void drawMap(Graphics2D g2) {
        int[][] map = gameModel.getTileManager().mapTileNum;
        for (int row = 0; row < Config.MAX_SCREEN_ROWS; row++) {
            for (int col = 0; col < Config.MAX_SCREEN_COLS; col++) {
                int tileType = map[col][row];
                int x = col * Config.TILE_SIZE;
                int y = row * Config.TILE_SIZE;

                if (tileType != 1 && tileType != 2) {
                    g2.drawImage(ImageLoader.get("grass"), x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                }

                switch (tileType) {
                    case 0:
                        g2.setColor(Color.WHITE);
                        g2.fillOval(x + 10, y + 10, 4, 4);
                        break;
                    case 3:
                        g2.setColor(Color.WHITE);
                        g2.fillOval(x + 6, y + 6, 12, 12);
                        break;
                    case 1:
                        g2.drawImage(ImageLoader.get("wall"), x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                        break;
                    case 2:
                        g2.drawImage(ImageLoader.get("iron"), x, y, Config.TILE_SIZE, Config.TILE_SIZE, null);
                        break;
                    case 4:
                        g2.setColor(new Color(255, 182, 255));
                        g2.fillRect(x, y + 10, Config.TILE_SIZE, 4);
                        break;
                }
            }
        }
    }

    private void drawPlayer(Graphics2D g2) {
        Player p = gameModel.getPlayer();
        String key = "pacman_" + p.direction + (p.spriteNum == 1 ? "_open" : "_close");
        BufferedImage img = ImageLoader.get(key);
        if (img != null) {
            g2.drawImage(img, p.x, p.y, Config.TILE_SIZE, Config.TILE_SIZE, null);
        }
    }

    private void drawGhosts(Graphics2D g2) {
        for (Ghost ghost : gameModel.getGhosts()) {
            String key = ghost.name.toLowerCase() + "_" + ghost.direction;
            BufferedImage img = ImageLoader.get(key);
            if (img != null) {
                g2.drawImage(img, ghost.x, ghost.y, Config.TILE_SIZE, Config.TILE_SIZE, null);
            }
        }
    }
}