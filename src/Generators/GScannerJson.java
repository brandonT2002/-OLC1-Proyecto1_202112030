package Generators;
import java.io.File;
public class GScannerJson {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/Language/ScannerJson.jflex"
            )
        );
    }
}