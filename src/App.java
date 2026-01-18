import javax.swing.JFrame;
import model.GameModel;
import view.GamePanel;
import controller.KeyHandler;
import controller.GameController;

public class App {
    public static void main(String[] args) {
        GameModel gameModel = new GameModel();
        KeyHandler keyH = new KeyHandler(gameModel);
        GamePanel gamePanel = new GamePanel(gameModel, keyH);
        GameController gameController = new GameController(gameModel, gamePanel, keyH);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pac-Man MVC");

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameController.startGameThread();
    }
}