package Home;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import Controller.Controller;
import Interface.Window;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        Controller controller = new Controller();
        Window w = new Window(controller);
        w.setVisible(true);
    }
}
