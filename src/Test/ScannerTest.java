package Test;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import Components.ErrorL;
import java_cup.runtime.Symbol;
import Language.Scanner;
public class ScannerTest {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("./Inputs/Print.sp");
            Scanner scanner = new Scanner(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            // System.out.println(input);
            Symbol token = null;
            do {
                    token = scanner.next_token();
                    // reporte += token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(6 - String.valueOf(token.left).length()) + token.right + " ".repeat(8 - String.valueOf(token.right).length()) + Language.TOK.terminalNames[token.sym];
            } while(token.value != null);
            System.out.println("TOKEN" + " ".repeat(35 - "TOKEN".length()) + "LINE" + " ".repeat(6 - "LINE".length()) + "COLUMN" + " ".repeat(8 - "COLUMN".length()) + "TYPE");
            saveReportTok(ReporteTokens(scanner, token));
            openReportTok("Reports/reporte_tokens.html");
            saveReportErrors(ReporteErrores(scanner.getErrors()));
            openReportErrors("Reports/reporte_errores.html");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public static String ReporteTokens(Scanner scanner, Symbol token) throws IOException {
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
                "            Tokens\r\n" + //
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
                "                        Token\u00F3n\r\n" + //
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

    public static String ReporteErrores(ArrayList<ErrorL> errores) {
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
                "            Errores L\u00E9xicos\r\n" + //
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

    public static void saveReportErrors(String reporteHTML) throws IOException {
        String fileName = "Reports/reporte_errores.html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reporteHTML);
        }
    }

    public static void openReportErrors(String fileName) throws IOException {
        File htmlFile = new File(fileName);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } else {
            System.out.println("La apertura automática del navegador no es compatible en este sistema.");
        }
    }

    public static void saveReportTok(String reporteHTML) throws IOException {
        String fileName = "Reports/reporte_tokens.html";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reporteHTML);
        }
    }

    public static void openReportTok(String fileName) throws IOException {
        File htmlFile = new File(fileName);
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(htmlFile.toURI());
        } else {
            System.out.println("La apertura automática del navegador no es compatible en este sistema.");
        }
    }

    public static String readInput(String path) {
        try {
            File archivo = new File(path);
            FileInputStream fis = new FileInputStream(archivo);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            
            String texto = "";
            String linea;
            while ((linea = br.readLine()) != null) {
                texto += linea + "\n";
            }
            br.close();
            fis.close();
            return texto;
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
}