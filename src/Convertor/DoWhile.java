package Convertor;
import java.util.ArrayList;
public class DoWhile extends Instruction {
    private ArrayList<Instruction> instructions;
    private Expression exp;
    public DoWhile(ArrayList<Instruction> instructions, Expression exp) {
        super("DOWHILE");
        this.instructions = instructions;
        this.exp = exp;
    }
    public String convert(int tab) {
        String code = "\t".repeat(tab);
        code += "while True:";
        if(instructions != null) {
            for(Instruction instruction : this.instructions) {
                if(!instruction.name.equals("EMPTY")) {
                    code += "\n" + instruction.convert(tab + 1);
                }
            }
            code += "\n" + "\t".repeat(tab + 1) + "if not (" + exp.convert() + "):";
            code += "\n" + "\t".repeat(tab + 2) + "break";
            return code;
        }
        return code + "\n" + "\t".repeat(tab + 1) + "pass";
    }
}
