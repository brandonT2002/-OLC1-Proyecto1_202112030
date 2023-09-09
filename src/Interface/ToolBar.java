package Interface;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.StyledDocument;
import Controller.Controller;
import Templates.Colors;
import Templates.Button;
import Templates.FunctionButton;
import Templates.Icons;

public class ToolBar extends JPanel implements MouseListener {
    Button newOLC, openOLC, saveAsOLC;
    Controller controller;
    File olcFile, auxiliar;
    FunctionButton close, minimize;
    IDE ide;
    JFileChooser file;
    JPanel div;
    Window w;

    public ToolBar(Controller controller, IDE ide, Window w) {
        this.w = w;
        this.ide = ide;
        this.controller = this.ide.controller;
        init();
        addOpenStat();
        addNewStat();
        addSaveAsStat();
        // addMinimizeButton();
        // addCloseButton();
        addDivisor();
    }

    private void addOpenStat() {
        openOLC = new Button("Abrir");
        openOLC.locationSize(20, 5, 50, 25);
        openOLC.text(Colors.WHITE, 14);
        openOLC.setDesign(Colors.MEDIUMCOLOR1);
        openOLC.setHoverColor(Colors.MEDIUMCOLOR2);
        openOLC.addMouseListener(this);
        this.add(openOLC);
    }

    private void addNewStat() {
        newOLC = new Button("Nuevo");
        newOLC.locationSize(72, 5, 60, 25);
        newOLC.text(Colors.WHITE, 14);
        newOLC.setDesign(Colors.MEDIUMCOLOR1);
        newOLC.setHoverColor(Colors.MEDIUMCOLOR2);
        newOLC.addMouseListener(this);
        this.add(newOLC);
    }

    private void addSaveAsStat() {
        saveAsOLC = new Button("Guardar Como");
        saveAsOLC.locationSize(134, 5, 110, 25);
        saveAsOLC.text(Colors.WHITE, 14);
        saveAsOLC.setDesign(Colors.MEDIUMCOLOR1);
        saveAsOLC.setHoverColor(Colors.MEDIUMCOLOR2);
        saveAsOLC.addMouseListener(this);
        this.add(saveAsOLC);
    }

    private void init() {
        this.setLayout(null);
        this.setBackground(Colors.MEDIUMCOLOR1);
        this.setVisible(true);
    }

    private void addDivisor() {
        div = new JPanel();
        div.setBackground(Colors.MEDIUMCOLOR2);
        div.setBounds(0, 35, 1920, 5);
        div.setVisible(true);
        this.add(div);
    }

    private void chooseFile() {
        this.file = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos StatPy-Estadisticas", "sp","json");
        file.setFileFilter(filtro);
        int seleccion = file.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            olcFile = file.getSelectedFile();
            int index = controller.existPJFile(olcFile.getAbsolutePath());
            if (index == -1) {
                controller.pjs.add(new IconFile(controller.countPJ(), olcFile, ide, controller));
                controller.serialize();
                controller.deserialize(ide);
                ide.lookPJFiles();
                controller.pjs.get(controller.countPJ() - 1).lookCode();
            } else {
                controller.pjs.get(index).lookCode();
            }
        }
    }

    private void createFile(String content) {
        this.file = new JFileChooser();
        this.file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = file.showDialog(null, "Seleccionar Directorio");
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            olcFile = file.getSelectedFile();
            String name;
            String path;
            String message = "Nombre del Archivo SP [.sp]:";
            ImageIcon icon = new ImageIcon(Icons.FILE1);
            Image img = icon.getImage();
            img = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            icon = new ImageIcon(img);
            do {
                name = (String) JOptionPane.showInputDialog(null, message, "Nuevo Proyecto", JOptionPane.PLAIN_MESSAGE, icon, null, null);
                if (name == null)
                    break;
                else if (name.replace(" ", "").equals("")) {
                    message = "Debe Ingresar un Nombre.\nNombre del Archivo SP [.sp]:";
                    continue;
                }
                path = olcFile.getAbsolutePath() + "\\" + name + ".sp";
                auxiliar = new File(path);
                if (!auxiliar.exists()) {
                    try {
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(auxiliar),
                                        "utf-8"));
                        writer.write(content);
                        writer.close();
                        controller.pjs.add(new IconFile(controller.countPJ(), auxiliar, ide, controller));
                        controller.serialize();
                        controller.deserialize(ide);
                        ide.lookPJFiles();
                        controller.pjs.get(controller.countPJ() - 1).lookCode();
                    } catch (Exception e1) {
                    }
                    break;
                }
                message = "El nuevo archivo no puede llamarse\ncon el mismo nombre de uno existente\nen el mismo directorio.\nNombre del Archivo PS [.ps]:";
            } while (true);
        }
    }

    private void createJsonFile(String content) {
    this.file = new JFileChooser();
    this.file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int seleccion = file.showDialog(null, "Seleccionar Directorio");
    if (seleccion == JFileChooser.APPROVE_OPTION) {
        olcFile = file.getSelectedFile();
        String name;
        String message = "Nombre del Archivo JSON [.json]:";
        String path;
        ImageIcon icon = new ImageIcon(Icons.FILE2);
        Image img = icon.getImage();
        img = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(img);
        do {
            name = (String) JOptionPane.showInputDialog(null, message, "Nuevo Archivo JSON", JOptionPane.PLAIN_MESSAGE, icon, null, null);
            if (name == null) {
                break;
            } else if (name.replace(" ", "").equals("")) {
                message = "Debe Ingresar un Nombre.\nNombre del Archivo JSON [.json]:";
                continue;
            }
            path = olcFile.getAbsolutePath() + "\\" + name + ".json";
            auxiliar = new File(path);
            if (!auxiliar.exists()) {
                try {
                        BufferedWriter writer = new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(auxiliar),
                                        "utf-8"));
                        writer.write(content);
                        writer.close();
                        controller.pjs.add(new IconFile(controller.countPJ(), auxiliar, ide, controller));
                        controller.serialize();
                        controller.deserialize(ide);
                        ide.lookPJFiles();
                        controller.pjs.get(controller.countPJ() - 1).lookCode();
                    } catch (Exception e1) {
                    }
                    break;
            }
            message = "El nuevo archivo no puede llamarse\ncon el mismo nombre de uno existente\nen el mismo directorio.\nNombre del Archivo JSON [.json]:";
        } while (true);
    }
}


    private void deleteFile(File file) {
        File[] archivos = file.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    deleteFile(archivo);
                } else {
                    archivo.delete();
                }
            }
        }
        file.delete();
    }

    private void deleteDirectories() {
        File file = new File("Dot");
        if (file.exists()) {
            deleteFile(file);
        }
        file = new File("Data");
        if (file.exists()) {
            deleteFile(file);
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == openOLC) {
            chooseFile();
        } else if (e.getSource() == newOLC) {
            try {
                StyledDocument doc = ide.editorArea.editor.getStyledDocument();
    
                String[] options = { "Crear archivo .sp", "Crear archivo .json" };
                int selectedOption = JOptionPane.showOptionDialog(
                    this,
                    "Seleccione la extensión para el nuevo archivo:",
                    "Nuevo Archivo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
                );
    
                if (selectedOption == 0) {
                    createFile("");;  // Guardar como .sp
                } else if (selectedOption == 1) {
                    createJsonFile("");  // Guardar como .json
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == saveAsOLC) {
            try {
                StyledDocument doc = ide.editorArea.editor.getStyledDocument();
                String input = doc.getText(0, doc.getLength());
    
                String[] options = { "Guardar como .sp", "Guardar como .json" };
                int selectedOption = JOptionPane.showOptionDialog(
                    this,
                    "Seleccione la extensión para guardar el archivo:",
                    "Guardar como",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
                );
    
                if (selectedOption == 0) {
                    createFile(input);  // Guardar como .sp
                } else if (selectedOption == 1) {
                    createJsonFile(input);  // Guardar como .json
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (e.getSource() == close) {
            deleteDirectories();
            System.exit(0);
        } else if (e.getSource() == minimize) {
            w.setState(1);
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}