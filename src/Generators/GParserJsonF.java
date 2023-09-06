package Generators;
public class GParserJsonF {
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
                            "TOKJSON",
                            "-parser",
                            "ParserJsonF",
                            "src/Painter/ParserJsonF.cup"
                    });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}