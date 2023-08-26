package Interface;

import javax.swing.JFrame;

import Templates.Colors;

public class Window extends JFrame {
    IDE ide;

    public Window() {
        super("StatPy");
        init();
        initComponents();
    }

    void initComponents() {
        ide = new IDE(this);
        this.getContentPane().setBackground(Colors.LIGHTCOLOR);
        this.getContentPane().add(ide);
    }

    void init () {
        this.setBounds(0, 0, 1920, 1058);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
