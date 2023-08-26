package Interface;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import Templates.Colors;
import Templates.Label;

public class IDE extends JPanel implements KeyListener {

    EditorArea editorArea;
    JPanel projects;
    JPanel editorAreaContent;
    JScrollPane outScroll;
    public JTextPane outConsole;
    Window w;

    public IDE (Window w) {
        this.w = w;
        init();
        initComponentes();
        defineComponents();
        addCOmponents();
        copyright();
    }

    void initComponentes() {
        projects = new JPanel();
        editorAreaContent = new JPanel();
        outConsole = new JTextPane();
    }

    void defineComponents() {
        // Barra de Proyectos
        projects.setBackground(Colors.MEDIUMCOLOR2);
        projects.setBounds(50, 105, 220, 808);
        projects.setLayout(null);
        // Editor
        editorAreaContent.setLayout(new BorderLayout());
        editorAreaContent.setBorder(BorderFactory.createLineBorder(Colors.DARKCOLOR, 8));
        editorArea = new EditorArea();
        editorArea.editor.addKeyListener(this);
        editorAreaContent.add(editorArea, BorderLayout.WEST);
        editorAreaContent.add(editorArea.scroll, BorderLayout.CENTER);
        editorAreaContent.setBounds(304, 105, 770, 808);
        editorArea.scroll.addKeyListener(this);
        // Consola
        outConsole.setEditable(false);
        outConsole.setForeground(Colors.WHITE);
        outConsole.setBackground(Colors.DARKCOLOR);
        outConsole.setText("StatPy:\n->");
        outConsole.setFont(new java.awt.Font("Consolas", 0, 13));
        outConsole.setBounds(0, 0, 770, 808);

        outScroll = new JScrollPane(outConsole);
        outScroll.setBorder(BorderFactory.createLineBorder(Colors.DARKCOLOR, 8));
        outScroll.setBounds(1100, 105, 770, 808);
    }

    void addCOmponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(outScroll);
    }

    void copyright() {
        this.add(new Label(0, 955, 1920, 15, "StatPy", 11));
        this.add(new Label(0, 970, 1920, 15, "© Brandon Andy Jefferson Tejaxún Pichiyá", 11));
    }

    void init() {
        this.setBackground(Colors.MEDIUMCOLOR1);
        this.setLayout(null);
    }

    // KeyListener
    void setFormat() {
        // controller.setFormat(editorArea.editor);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        setFormat();
    }

    public void keyReleased(KeyEvent e) {
        setFormat();
    }
}
