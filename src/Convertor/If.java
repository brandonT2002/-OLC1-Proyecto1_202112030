package Convertor;
import java.util.ArrayList;
public class If extends Instruction {
    private Expression exp;
    private ArrayList<Instruction> instructions;
    private ArrayList<Instruction> excepts;
    private Instruction except;
    // PARA IF
    public If(Expression exp, ArrayList<Instruction> instructions){
        super("IF");
        this.exp = exp;
        this.instructions = instructions;
    }
    // PARA IF CON ELSE IF
    public If(Expression exp, ArrayList<Instruction> instructions, Instruction except){
        super("IF");
        this.exp = exp;
        this.instructions = instructions;
        this.except = except;
    }
    // PARA IF CON ELSE
    public If(Expression exp, ArrayList<Instruction> instructions, ArrayList<Instruction> excepts){
        super("IF");
        this.exp = exp;
        this.instructions = instructions;
        this.excepts = excepts;
    }
    public String convert(int tab) {
        String code = "\t".repeat(tab);
        code += "if " + exp.convert() + ":";
        if(instructions != null) {
            for(Instruction instruction : this.instructions) {
                if(!instruction.name.equals("EMPTY")) {
                    code += "\n" + instruction.convert(tab + 1);
                }
            }
        }
        else {
            code += "\n" + "\t".repeat(tab + 1) + "pass";
        }
        if(except != null) {
            code += "\n" + "\t".repeat(tab) + "el" + except.convert(tab).substring(tab);
        }
        if(excepts != null) {
            code += "\n" + "\t".repeat(tab) + "else:";
            for(Instruction instruction : this.excepts) {
                if(!instruction.name.equals("EMPTY")) {
                    code += "\n" + instruction.convert(tab + 1);
                }
            }
        }
        return code;
    }
}
