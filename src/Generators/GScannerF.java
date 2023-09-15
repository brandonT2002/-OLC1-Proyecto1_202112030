package Generators;
import java.io.File;
public class GScannerF {
    public static void main(String[] args) {
        jflex.Main.generate(
            new File(
                "src/Painter/ScannerF.jflex"
            )
        );
    }
}