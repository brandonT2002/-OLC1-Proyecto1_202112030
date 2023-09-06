package Convertor;
public class Print extends Instruction {
    private Expression exp;
    public Print(Expression exp) {
        super("PRINT");
        this.exp = exp;
    }
    public String convert(int tab) {
        String tabs = "\t".repeat(tab);
        if(exp != null) {
            return tabs + "print(" + exp.convert() + ")";
        }
        return tabs + "print()";
    }
}