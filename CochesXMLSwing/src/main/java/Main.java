import controller.CocheController;
import model.CocheManager;
import view.MainFrame;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            CocheManager model = new CocheManager();
            new CocheController(frame, model);
            frame.setVisible(true);
        });
    }
}