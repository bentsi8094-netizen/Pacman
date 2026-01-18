import javax.swing.JFrame;
import controller.GameController;
import model.GameModel;
import view.GamePanel;

public class App {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Pacman Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GameModel gameModel = new GameModel();
        GamePanel panel = new GamePanel(gameModel);
        GameController gameController = new GameController(gameModel, panel);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        gameController.startGame();
    }
}