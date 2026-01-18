
import javax.swing.JFrame;
import controller.GameController;
import model.GameModel;
import view.GamePanel;

public class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pacman Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GameModel gameModel = new GameModel();
        GamePanel gamePanel = new GamePanel(gameModel);
        // ה-Controller מנהל את הקשר ביניהם
        new GameController(gameModel, gamePanel);

        frame.add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}