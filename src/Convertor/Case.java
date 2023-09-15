package Convertor;

import java.util.ArrayList;

public class Case extends Instruction {
    private Expression _case;
    private ArrayList<Instruction> block;
    public Case(Expression _case, ArrayList<Instruction> block) {
        super("CASE");
        this._case = _case;
        this.block = block;
    }
    public String convert(int tab) {
        String code = "\t".repeat(tab);
        code += this._case.convert() + ": ";
        if(block != null) {
            for(Instruction instruction : block) {
                if(instruction != null) {
                    code += instruction.convert(0);
                }
            }
            code += ",";
            return code;
        }
        return code + "\n" + "\t".repeat(tab + 1) + "pass";
    }
}
