package Generators;
import java.io.File;
public class GScannerJsonF {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/Painter/ScannerJsonF.jflex"
            )
        );
    }
}