package Interface;

import javax.swing.JFrame;
import Controller.Controller;
import Templates.Colors;

public class Window extends JFrame {
    Controller controller;
    IDE ide;
    // public static String iconMin = "Images/cubo1.png";
    // ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("Images/cubo1.png"));
    public Window(Controller controller) {
        super("Mini J");
        this.controller = controller;
        init();
        initComponents();
    }

    void initComponents() {
        ide = new IDE(this);
        this.getContentPane().setBackground(Colors.LIGHTCOLOR);
        this.getContentPane().add(ide);
        controller.deserialize(ide);
        ide.lookPJFiles();
    }

    void init() {
        // this.setUndecorated(true);
        // this.setResizable(false);
        this.setBounds(0, 0, 1920, 1058);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
