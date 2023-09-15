package Controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import Components.ErrorL;
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
import java_cup.runtime.Symbol;
import Interface.IDE;
import Interface.IconFile;
import Interface.Path;

public class Controller {
    public ArrayList<IconFile> pjs = new ArrayList<>();
    public TableSym tableSym = new TableSym();
    Parser parser;

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
                Symbol token = null;
                EnvGraph envG = new EnvGraph();
                parser = new Parser(scanner, tableSym, envG);
                parser.parse();
                if (parser.isSuccessExecution()) {
                    String outPrint = "StatPy: " + currentFile.name + "\n\n";
                    outPrint += parser.mainMethod.convert(0);
                    ide.outConsole.setText(outPrint);
                    saveReportTok(ReporteTokens(input, token, currentFile.name), currentFile.name);
                    saveReportErrors(ReporteErrores(scanner.getErrors(), currentFile.name), currentFile.name);
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
                Symbol token = null;
                ParserJson parser = new ParserJson(scanner, tableSym);
                parser.setFileName(currentFile.name);
                parser.parse();
                if (parser.isSuccessExecution()) {
                    String outPrint = "StatPy: " + currentFile.name + "\n\n";
                    outPrint += "-> Archivo Json analizado correctamente\n";
                    outPrint += "-> Valores Almacenados";
                    ide.outConsole.setText(outPrint);
                    saveReportTok(ReporteTokens_(input, token, currentFile.name), currentFile.name);
                    saveReportErrors(ReporteErrores_(scanner.getErrors(), currentFile.name), currentFile.name);
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
            parser.defineGlobals();
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

    // Reporte de Tokens
    public static void saveReportTok(String reporteHTML, String name) throws IOException {
        String fileName = "Reports/reporte_tokens_" + name + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reporteHTML);
        }
    }

    public static String ReporteTokens(String input, Symbol token, String name) throws IOException {
        Scanner scanner = new Scanner(
                    new BufferedReader(
                        new StringReader(input)
                    )
                );

        String reporte = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"es\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>StatPy</title>\r\n" + //
                "    <link rel=\"stylesheet\" href=\"css/normalize.css\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + //
                "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap\" rel=\"stylesheet\">\r\n" + //
                "    <link rel=\"stylesheet\" href=\"style.css\">\r\n" + //
                "    <script src=\"./js/Headers.js\"></script>\r\n" + //
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js\"></script>\r\n" + //
                "</head>\r\n" + //
                "<body>\r\n" + //
                "    <div class=\"container\">\r\n" + //
                "        <h1 class=\"center-text\">\r\n" + //
                "            Tokens " + name + "\r\n" + //
                "        </h1>\r\n" + //
                "    </div>\r\n" + //
                "    <div class=\"containerTable\">\r\n" + //
                "        <div class=\"container container-table\">\r\n" + //
                "            <table id=\"compoundsInfo\">\r\n" + //
                "                <tr>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Lexema\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Token\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        L\u00EDnea\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Columna\r\n" + //
                "                    </th>\r\n" + //
                "                </tr>";
        do {
                token = scanner.next_token();
                reporte += "<tr>";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + token.value +"</td>\n";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + Language.TOK.terminalNames[token.sym] +"</td>\n";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + token.left +"</td>\n";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + token.right +"</td>\n";
                reporte += "</tr>\n";
                // reporte += token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(6 - String.valueOf(token.left).length()) + token.right + " ".repeat(8 - String.valueOf(token.right).length()) + Language.TOK.terminalNames[token.sym];
        } while(token.value != null);
        reporte += "\t\t</table>\r\n" + //
                "        </div>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "</html>";
        return reporte;
    }

    public static String ReporteTokens_(String input, Symbol token, String name) throws IOException {
        ScannerJson scanner = new ScannerJson(
                    new BufferedReader(
                        new StringReader(input)
                    )
                );

        String reporte = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"es\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>StatPy</title>\r\n" + //
                "    <link rel=\"stylesheet\" href=\"css/normalize.css\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + //
                "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap\" rel=\"stylesheet\">\r\n" + //
                "    <link rel=\"stylesheet\" href=\"style.css\">\r\n" + //
                "    <script src=\"./js/Headers.js\"></script>\r\n" + //
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js\"></script>\r\n" + //
                "</head>\r\n" + //
                "<body>\r\n" + //
                "    <div class=\"container\">\r\n" + //
                "        <h1 class=\"center-text\">\r\n" + //
                "            Tokens " + name + "\r\n" + //
                "        </h1>\r\n" + //
                "    </div>\r\n" + //
                "    <div class=\"containerTable\">\r\n" + //
                "        <div class=\"container container-table\">\r\n" + //
                "            <table id=\"compoundsInfo\">\r\n" + //
                "                <tr>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Lexema\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Token\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        L\u00EDnea\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Columna\r\n" + //
                "                    </th>\r\n" + //
                "                </tr>";
        do {
                token = scanner.next_token();
                reporte += "<tr>";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + token.value +"</td>\n";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + Language.TOKJSON.terminalNames[token.sym] +"</td>\n";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + token.left +"</td>\n";
                reporte += "\n\t\t\t\t<td class=\"center-text\">" + token.right +"</td>\n";
                reporte += "</tr>\n";
                // reporte += token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(6 - String.valueOf(token.left).length()) + token.right + " ".repeat(8 - String.valueOf(token.right).length()) + Language.TOK.terminalNames[token.sym];
        } while(token.value != null);
        reporte += "\t\t</table>\r\n" + //
                "        </div>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "</html>";
        return reporte;
    }

    public static void saveReportErrors(String reporteHTML, String name) throws IOException {
        String fileName = "Reports/reporte_errores_" + name + ".html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reporteHTML);
        }
    }

    public static String ReporteErrores(ArrayList<ErrorL> errores, String name) {
        String reporte = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"es\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>StatPy</title>\r\n" + //
                "    <link rel=\"stylesheet\" href=\"css/normalize.css\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + //
                "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap\" rel=\"stylesheet\">\r\n" + //
                "    <link rel=\"stylesheet\" href=\"style.css\">\r\n" + //
                "    <script src=\"./js/Headers.js\"></script>\r\n" + //
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js\"></script>\r\n" + //
                "</head>\r\n" + //
                "<body>\r\n" + //
                "    <div class=\"container\">\r\n" + //
                "        <h1 class=\"center-text\">\r\n" + //
                "            Errores L\u00E9xicos " + name + "\r\n" + //
                "        </h1>\r\n" + //
                "    </div>\r\n" + //
                "    <div class=\"containerTable\">\r\n" + //
                "        <div class=\"container container-table\">\r\n" + //
                "            <table id=\"compoundsInfo\">\r\n" + //
                "                <tr>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Lexema\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Descripci\u00F3n\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        L\u00EDnea\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Columna\r\n" + //
                "                    </th>\r\n" + //
                "                </tr>";
        for(ErrorL error : errores) {
                reporte += error.getHTML();
        }
        reporte += "\t\t</table>\r\n" + //
                "        </div>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "</html>";
        return reporte;
    }

    public static String ReporteErrores_(ArrayList<ErrorL> errores, String name) {
        String reporte = "<!DOCTYPE html>\r\n" + //
                "<html lang=\"es\">\r\n" + //
                "<head>\r\n" + //
                "    <meta charset=\"UTF-8\">\r\n" + //
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" + //
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
                "    <title>StatPy</title>\r\n" + //
                "    <link rel=\"stylesheet\" href=\"css/normalize.css\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n" + //
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n" + //
                "    <link href=\"https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap\" rel=\"stylesheet\">\r\n" + //
                "    <link rel=\"stylesheet\" href=\"style.css\">\r\n" + //
                "    <script src=\"./js/Headers.js\"></script>\r\n" + //
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/PapaParse/5.3.0/papaparse.min.js\"></script>\r\n" + //
                "</head>\r\n" + //
                "<body>\r\n" + //
                "    <div class=\"container\">\r\n" + //
                "        <h1 class=\"center-text\">\r\n" + //
                "            Errores L\u00E9xicos " + name + "\r\n" + //
                "        </h1>\r\n" + //
                "    </div>\r\n" + //
                "    <div class=\"containerTable\">\r\n" + //
                "        <div class=\"container container-table\">\r\n" + //
                "            <table id=\"compoundsInfo\">\r\n" + //
                "                <tr>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Lexema\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Descripci\u00F3n\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        L\u00EDnea\r\n" + //
                "                    </th>\r\n" + //
                "                    <th id=\"noBooks\" class=\"center-text\">\r\n" + //
                "                        Columna\r\n" + //
                "                    </th>\r\n" + //
                "                </tr>";
        for(ErrorL error : errores) {
                reporte += error.getHTML();
        }
        reporte += "\t\t</table>\r\n" + //
                "        </div>\r\n" + //
                "    </div>\r\n" + //
                "</body>\r\n" + //
                "</html>";
        return reporte;
    }

}