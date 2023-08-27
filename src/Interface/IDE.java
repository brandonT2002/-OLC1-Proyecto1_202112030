package Interface;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java_cup.runtime.Symbol;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import Controller.Controller;
import Templates.Button;
import Templates.Colors;
import Templates.Icons;
import Templates.Label;
import Templates.RadioButton;

public class IDE extends JPanel
        implements ActionListener, KeyListener, MouseWheelListener, MouseListener, MouseMotionListener {
    Controller controller;
    Button analyzeInput, uploadOuts, saveOLC;
    public JComboBox<String> regexCB;
    public double zoomFactor = 1.05; // factor de zoom
    EditorArea editorArea;
    public Icon icono;
    public ImageIcon image;
    int indexFilePJ = -1;
    int posCaret, posXImg, posYImg, posXLabImg, posYLabImg;
    public JLabel img;
    JPanel editorAreaContent;
    JPanel editorAreaContentFalse;
    // public JPanel graphics;
    JPanel projects;
    JScrollPane outScroll;
    public JTextPane outConsole;
    public RadioButton treesR, nextsR, transitionsR, afdsR, afndsR;
    String input;
    Symbol sym;
    Tag tag;
    ToolBar toolbar;
    Window w;

    public IDE(Window w) {
        this.w = w;
        this.controller = w.controller;
        init();
        initComponents();
        defineComponents();
        addComponents();
        addToolBar();
        lookPJFiles();
        initManagerGraphs();
        hideManagerGraphs();
        copyright();
    }

    void initComponents() {
        projects = new JPanel();
        editorAreaContent = new JPanel();
        outConsole = new JTextPane();
        // graphics = new JPanel();
        analyzeInput = new Button();
        uploadOuts = new Button();
        saveOLC = new Button();
        treesR = new RadioButton();
        nextsR = new RadioButton();
        transitionsR = new RadioButton();
        afdsR = new RadioButton();
        afndsR = new RadioButton();
        regexCB = new JComboBox<>();
    }

    void defineComponents() {
        // projects
        projects.setBackground(Colors.MEDIUMCOLOR2);
        projects.setBounds(50, 105, 220, 808);
        projects.setLayout(null);
        // editorArea
        editorAreaContent.setLayout(new BorderLayout());
        editorAreaContent.setBorder(BorderFactory.createLineBorder(Colors.DARKCOLOR, 8));
        editorArea = new EditorArea();
        editorArea.editor.addKeyListener(this);
        editorAreaContent.add(editorArea, BorderLayout.WEST);
        editorAreaContent.add(editorArea.scroll, BorderLayout.CENTER);
        editorAreaContent.setBounds(304, 105, 770, 808);
        editorArea.scroll.addKeyListener(this);
        // out console
        outConsole.setEditable(false);
        outConsole.setForeground(Colors.WHITE);
        outConsole.setBackground(Colors.DARKCOLOR);
        outConsole.setText("StatPy:\n->");
        outConsole.setFont(new java.awt.Font("Consolas", 0, 13));
        outConsole.setBounds(0, 0, 770, 808);

        outScroll = new JScrollPane(outConsole);
        outScroll.setBorder(BorderFactory.createLineBorder(Colors.DARKCOLOR, 8));
        outScroll.setBounds(1100, 105, 770, 808);
        // graphics
        /*
         * graphics.setBackground(Colors.DARKVSCODE);
         * graphics.setBounds(1100, 105, 770, 620);
         * graphics.setBorder(BorderFactory.createEmptyBorder());
         * graphics.setLayout(null);
         */
        // analyzeInput
        analyzeInput.locationSize(624, 56, 30, 30);
        analyzeInput.Icon(Icons.PLAY);
        analyzeInput.setDesign(Colors.COLOR2);
        analyzeInput.setHoverColor(Colors.COLOR3);
        analyzeInput.addMouseListener(this);
        // uploadOuts
        uploadOuts.locationSize(659, 56, 30, 30);
        uploadOuts.Icon(Icons.UPLOAD);
        uploadOuts.setDesign(Colors.COLOR2);
        uploadOuts.setHoverColor(Colors.COLOR3);
        uploadOuts.addMouseListener(this);
        // saveOLC
        saveOLC.locationSize(694, 56, 30, 30);
        saveOLC.Icon(Icons.SAVE);
        saveOLC.setDesign(Colors.COLOR2);
        saveOLC.setHoverColor(Colors.COLOR3);
        saveOLC.addMouseListener(this);
    }

    public void initManagerGraphs() {
        ButtonGroup group = new ButtonGroup();

        treesR.setText("Árbol", Colors.WHITE, 12);
        treesR.setBoundsR(790, 50, 53, 15);
        treesR.setSelected(true);
        treesR.addActionListener(this);
        group.add(treesR);
        this.add(treesR);

        nextsR.setText("Siguientes", Colors.WHITE, 12);
        nextsR.setBoundsR(790, 65, 84, 15);
        nextsR.addActionListener(this);
        group.add(nextsR);
        this.add(nextsR);

        transitionsR.setText("Transiciones", Colors.WHITE, 12);
        transitionsR.setBoundsR(790, 80, 94, 15);
        transitionsR.addActionListener(this);
        group.add(transitionsR);
        this.add(transitionsR);

        afdsR.setText("AFD", Colors.WHITE, 12);
        afdsR.setBoundsR(890, 50, 44, 15);
        afdsR.addActionListener(this);
        group.add(afdsR);
        this.add(afdsR);

        afndsR.setText("AFND", Colors.WHITE, 12);
        afndsR.setBoundsR(890, 65, 52, 15);
        afndsR.addActionListener(this);
        group.add(afndsR);
        this.add(afndsR);

        regexCB.setBounds(1000, 60, 190, 22);
        regexCB.addActionListener(this);
        this.add(regexCB);
    }

    public void hideManagerGraphs() {
        treesR.setVisible(false);
        nextsR.setVisible(false);
        transitionsR.setVisible(false);
        afdsR.setVisible(false);
        afndsR.setVisible(false);
        regexCB.setVisible(false);
    }

    public void showManagerGraphs() {
        treesR.setVisible(true);
        nextsR.setVisible(true);
        transitionsR.setVisible(true);
        afdsR.setVisible(true);
        afndsR.setVisible(true);
        regexCB.setVisible(true);
    }

    public void updateTag() {
        if (tag != null) {
            tag.removeAll();
            this.remove(tag);
        }
        tag = new Tag(indexFilePJ, this, controller);
        this.add(tag);
        this.repaint();
    }

    public void lookPJFiles() {
        projects.removeAll();
        projects.add(new Label(0, 10, projects.getWidth(), 25, "Proyectos", 16));
        for (int i = 0; i < controller.countPJ(); i++) {
            controller.pjs.get(i).locationSize(0, i * 25 + 40, this.projects.getWidth(), 25);
            controller.pjs.get(i).setHoverColor(Colors.LIGHTCOLOR);
            projects.add(controller.pjs.get(i));
        }
        projects.repaint();
    }

    void addComponents() {
        this.add(projects);
        this.add(editorAreaContent);
        this.add(outScroll);
        // this.add(graphics);
        this.add(analyzeInput);
        this.add(uploadOuts);
        this.add(saveOLC);
    }

    void copyright() {
        this.add(new Label(0, 955, 1920, 15, "StatPy", 11));
        this.add(new Label(0, 970, 1920, 15, "© Brandon Andy Jefferson Tejaxún Pichiyá", 11));
    }

    void addToolBar() {
        toolbar = new ToolBar(controller, this, w);
        toolbar.setBounds(0, 0, 1920, 40);
        this.add(toolbar);
    }

    void init() {
        this.setBackground(Colors.MEDIUMCOLOR1);
        this.setLayout(null);
    }

    void execute() {
        controller.setFormat(editorArea.editor);
        controller.analyze(this, indexFilePJ, editorArea.editor);
    }

    void setFormat() {
        controller.setFormat(editorArea.editor);
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        setFormat();
    }

    public void keyReleased(KeyEvent e) {
        setFormat();
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        try {
            int notches = e.getWheelRotation();
            if (notches < 0) {
                // zoom in
                zoomFactor *= 1.05;
            } else {
                // zoom out
                zoomFactor /= 1.05;
            }
            int w = image.getIconWidth();
            int h = image.getIconHeight();
            img.removeAll();
            icono = new ImageIcon(image.getImage().getScaledInstance((int) (w * zoomFactor), (int) (h * zoomFactor),
                    Image.SCALE_DEFAULT));
            img.setIcon(icono);
            img.setSize(icono.getIconWidth(), icono.getIconHeight());
            // graphics.revalidate();
            // graphics.repaint();
        } catch (Exception e1) {
        }
    }

    public void mouseDragged(MouseEvent e) {
        try {
            int dx = e.getX() - posXImg;
            int dy = e.getY() - posYImg;
            img.setLocation(posXLabImg + dx, posYLabImg + dy);
        } catch (Exception e1) {
        }
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == analyzeInput) {
            if (indexFilePJ != -1) {
                execute();
            } else {
                outConsole.setText("StatPy:\n->");
            }
        } else if (e.getSource() == uploadOuts) {
            if (indexFilePJ != -1) {
                // controller.validateString(indexFilePJ,editorArea.editor,console);
            } else {
                outConsole.setText("StatPy:\n->");
            }
        } else if (e.getSource() == saveOLC) {
            if (indexFilePJ != -1) {
                controller.saveOLCPJ(indexFilePJ, editorArea.editor);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        try {
            posXImg = e.getX();
            posYImg = e.getY();
            posXLabImg = img.getX();
            posYLabImg = img.getY();
        } catch (Exception e1) {
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == treesR || e.getSource() == nextsR || e.getSource() == transitionsR
                || e.getSource() == afdsR || e.getSource() == afndsR || e.getSource() == regexCB) {
            controller.lookGraphs(this, indexFilePJ);
        }
    }
}