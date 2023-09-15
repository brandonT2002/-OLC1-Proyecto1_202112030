package Convertor;
import java.util.ArrayList;
public class MainMethod extends Instruction {
    private ArrayList<Instruction> instructions;
    private String callMain;
    public MainMethod(ArrayList<Instruction> instructions) {
        super("MAINMETHOD");
        this.instructions = instructions;
        this.callMain = "\n\nif __name__ == \"__main__\":\n\tmain()";
    }
    public String convert(int tab) {
        String code = "def main():";
        if(instructions != null) {
            String instructionsM = "";
            for(Instruction instruction : this.instructions) {
                if(!instruction.name.equals("EMPTY")) {
                    instructionsM += "\n" + instruction.convert(tab + 1);
                }
            }
            return code + (!instructionsM.isEmpty() ? instructionsM : "\n\tpass") + callMain;
        }
        return code + "\n\tpass" + callMain;
    }
}