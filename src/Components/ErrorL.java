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
}
