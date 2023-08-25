package Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import Language.ParserJson;
import Language.ScannerJson;
public class ParserTestJson {
    public static void main(String[] args) throws Exception {
        try {
            String input = readInput("./Inputs/ejemploJSON.json");
            ScannerJson scanner = new ScannerJson(
                new BufferedReader(
                    new StringReader(input)
                )
            );
            ParserJson parser = new ParserJson(scanner);
            parser.parse();
            System.out.println("-> " + parser.getErrors());
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