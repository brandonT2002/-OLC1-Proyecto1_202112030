package Components;

public class ErrorL {
    int line;
    int column;
    String character;
    public ErrorL(int line, int column, String character) {
        this.line = line;
        this.column = column;
        this.character = character;
    }
    public void print() {
        System.out.printf("%-6s%-8s%-10s\n",
            line,
            column,
            "Unrecognized Character: " + character
        );
    }
    public String toString() {
        return line + " ".repeat(6 - String.valueOf(line).length()) + column + " ".repeat(8 - String.valueOf(column).length()) + "Unrecognized Character: " + character;
    }
}
