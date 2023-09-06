package Generators;
public class GParserF {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                    new String[] {
                            "-destdir",
                            "src/Painter",
                            "-symbols",
                            "TOK",
                            "-parser",
                            "ParserF",
                            "src/Painter/ParserF.cup"
                    });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}