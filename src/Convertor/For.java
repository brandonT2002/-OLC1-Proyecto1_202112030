package Convertor;
import java.util.ArrayList;
public class For extends Instruction {
    private String id;
    private String start;
    private String limit;
    private ArrayList<Instruction> instructions;
    public For(String id, String start, String limit, ArrayList<Instruction> instructions) {
        super("FOR");
        this.id = id;
        this.start = start;
        this.limit = limit;
        this.instructions = instructions;
    }
    public String convert(int tab) {
        String code = "\t".repeat(tab);
        code += "for " + id + " in range(" + start + ", " + limit + "):";
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