package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import Components.ErrorL;
import java_cup.runtime.Symbol;
import Language.ScannerJson;
public class ScannerTestJson {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("./Inputs/ejemploJSON.json");
            ScannerJson scanner = new ScannerJson(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            System.out.println(input);
            Symbol token;
            System.out.println("TOKEN" + " ".repeat(35 - "TOKEN".length()) + "LINE" + " ".repeat(6 - "LINE".length()) + "COLUMN" + " ".repeat(8 - "COLUMN".length()) + "TYPE");
            do {
                token = scanner.next_token();
                System.out.println(token.value + " ".repeat(35 - String.valueOf(token.value).length()) + token.left + " ".repeat(6 - String.valueOf(token.left).length()) + token.right + " ".repeat(8 - String.valueOf(token.right).length()) + Language.TOKJSON.terminalNames[token.sym]);
            } while(token.value != null);
            for(ErrorL error : scanner.getErrors()) {
                System.out.println(error);
            }
        }
        catch(Exception e) {
            System.out.println(e);
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