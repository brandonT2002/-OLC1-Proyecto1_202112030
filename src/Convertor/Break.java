package Convertor;

public class Break extends Instruction {
    public Break() {
        super("BREAK");
    }
    public String convert(int tab) {
        return "\t".repeat(tab) + "break";
    }
}
