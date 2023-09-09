package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import Graphs.EnvGraph;
import Language.Parser;
import Language.ParserJson;
import Language.Scanner;
import Language.ScannerJson;
import Painter.ParserF;
import Painter.ScannerF;
import Painter.ParserJsonF;
import Painter.ScannerJsonF;
import Painter.WordPainter;
import TableSym.TableSym;
import Interface.IDE;
import Interface.IconFile;
import Interface.Path;

public class Controller {
    public ArrayList<IconFile> pjs = new ArrayList<>();
    public TableSym tableSym = new TableSym();
    Parser parser;
    boolean thereGraph = false;

    public int existPJFile(String path) {
        for (int i = 0; i < pjs.size(); i++) {
            if (pjs.get(i).path.equals(path)) {
                return i;
            }
        }
        return -1;
    }

    public int countPJ() {
        return pjs.size();
    }

    public void setFormat(JTextPane editor, int index) {
        try {
            IconFile currentFile = pjs.get(index);
            int indexP = currentFile.name.lastIndexOf(".");
            String ext = currentFile.name.substring(indexP + 1);
            // System.out.println(ext);
            if(ext.equals("sp")) {
                StyledDocument doc = editor.getStyledDocument();
                String input = doc.getText(0, doc.getLength());
                WordPainter painter = new WordPainter();
                ScannerF scanner = new ScannerF(
                    new BufferedReader(
                        new StringReader(input)),
                        painter);
                painter.setStyle(editor);
                ParserF parser = new ParserF(scanner, painter);
                parser.parse();
            }
            else {
                StyledDocument doc = editor.getStyledDocument();
                String input = doc.getText(0, doc.getLength());
                WordPainter painter = new WordPainter();
                ScannerJsonF scanner = new ScannerJsonF(
                        new BufferedReader(
                                new StringReader(input)),
                        painter);
                painter.setStyle(editor);
                ParserJsonF parser = new ParserJsonF(scanner, painter);
                parser.parse();
            }
        } catch (Exception e) {
            // System.out.println(e);
        }
    }

    public void analyze(IDE ide, int index, JTextPane editor) {
        try {
            IconFile currentFile = pjs.get(index);
            int indexP = currentFile.name.lastIndexOf(".");
            String ext = currentFile.name.substring(indexP + 1);
            if(ext.equals("sp")) {
                StyledDocument doc = editor.getStyledDocument();
                String input = doc.getText(0, doc.getLength());
                Scanner scanner = new Scanner(
                    new BufferedReader(
                        new StringReader(input)
                    )
                );
                EnvGraph envG = new EnvGraph();
                parser = new Parser(scanner, tableSym, envG);
                parser.parse();
                if (parser.isSuccessExecution()) {
                    String outPrint = "StatPy: " + currentFile.name + "\n\n";
                    outPrint += parser.mainMethod.convert(0);
                    ide.outConsole.setText(outPrint);
                } else {
                    ide.outConsole.setText("StatPy: " + currentFile.name + "\n-> " + parser.getErrors());
                }
            }
            else {
                StyledDocument doc = editor.getStyledDocument();
                String input = doc.getText(0, doc.getLength());
                ScannerJson scanner = new ScannerJson(
                    new BufferedReader(
                        new StringReader(input)
                    )
                );
                ParserJson parser = new ParserJson(scanner, tableSym);
                parser.setFileName(currentFile.name);
                parser.parse();
                if (parser.isSuccessExecution()) {
                    String outPrint = "StatPy: " + currentFile.name + "\n\n";
                    outPrint += "-> Archivo Json analizado correctamente\n";
                    outPrint += "-> Valores Almacenados";
                    ide.outConsole.setText(outPrint);
                } else {
                    tableSym.filesJSON.remove(currentFile.name);
                    ide.outConsole.setText("StatPy: " + currentFile.name + "\n-> " + parser.getErrors());
                }
            }
        }
        catch(Exception e) {}
    }

    public void graph() {
        if (parser != null) {
            if (parser.isThereGraphBar) {
                parser.barGraph();
            }
            if (parser.isThereGraphPie) {
                parser.pieChart();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha leido ning\u00FAn archivo", "Gráficas", 0);
        }
    }

    public void saveStatPyPJ(int index, JTextPane editor) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(pjs.get(index).path),
                            "utf-8"));
            StyledDocument doc = editor.getStyledDocument();
            String input = doc.getText(0, doc.getLength());
            writer.write(input);
            writer.close();
        } catch (Exception e) {
        }
    }

    public String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String text = "";
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
                if (br.ready()) {
                    text += "\n";
                }
            }
            br.close();
            fis.close();
            return text;
        } catch (Exception e) {
            return "java.io.FileNotFoundException";
        }
    }

    public void serialize() {
        try {
            Path[] pjs1 = new Path[pjs.size()];
            for (int i = 0; i < pjs.size(); i++) {
                pjs1[i] = new Path(pjs.get(i).id, pjs.get(i).path);
            }
            FileOutputStream file = new FileOutputStream("bin/Files");
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(pjs1);
            output.close();
            file.close();
        } catch (Exception e) {
        }
    }

    public void deserialize(IDE ide) {
        try {
            FileInputStream file = new FileInputStream("bin/Files");
            ObjectInputStream input = new ObjectInputStream(file);
            Path[] pjs1 = (Path[]) input.readObject();
            input.close();
            file.close();
            pjs = new ArrayList<>();
            int i = 0;
            for (Path path : pjs1) {
                pjs.add(new IconFile(i, new File(path.path), ide, this));
                i++;
            }
        } catch (Exception e) {
        }
    }
}