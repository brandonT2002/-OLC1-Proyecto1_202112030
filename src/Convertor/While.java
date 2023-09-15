package Convertor;
import java.util.ArrayList;
public class While extends Instruction {
    private Expression exp;
    private ArrayList<Instruction> instructions;
    public While(Expression exp, ArrayList<Instruction> instructions) {
        super("WHILE");
        this.exp = exp;
        this.instructions = instructions;
    }
    public String convert(int tab) {
        String code = "\t".repeat(tab);
        code += "while " + exp.convert() + ":";
        if(instructions != null) {
            for(Instruction instruction : this.instructions) {
                if(!instruction.name.equals("EMPTY")) {
                    code += "\n" + instruction.convert(tab + 1);
                }
            }
            return code;
        }
        return code + "\n" + "\t".repeat(tab + 1) + "pass";
    }
    
}
