package Convertor;

import java.util.ArrayList;
public class SwitchCase extends Instruction {
    private Expression exp;
    private ArrayList<Case> cases;
    private ArrayList<Instruction> _default;
    public SwitchCase(Expression exp, ArrayList<Case> cases, ArrayList<Instruction> _default){
        super("SWITCHCASE");
        this.exp = exp;
        this.cases = cases;
        this._default = _default;
    }
    public String convert(int tab) {
        String code = "\t".repeat(tab);
        System.out.println("EMTRA AQUI");
        code += "def switch(cases, " + exp.convert() + "):";
        if(cases != null) {
            for(Case case_ : cases) {
                code += "\n" + case_.convert(tab + 1);
            }
        }
        if(_default != null) {
            code += "\n"+ "\t".repeat(tab + 1) + "default: ";
            for(Instruction instruction : _default) {
                if(instruction != null) {
                    code += instruction.convert(0);
                }
            }
            return code;
        }
        return code + "\n" + "\t".repeat(tab + 1) + "pass";
    }
}
