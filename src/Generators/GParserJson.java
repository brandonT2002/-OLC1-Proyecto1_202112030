package Generators;
public class GParserJson {
    public static void main(String[] args) {
        generate();
    }
    public static void generate() {
        try {
            java_cup.Main.main(
                    new String[] {
                            "-destdir",
                            "src/Language",
                            "-symbols",
                            "TOKJSON",
                            "-parser",
                            "ParserJson",
                            "src/Language/ParserJson.cup"
                    });
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}