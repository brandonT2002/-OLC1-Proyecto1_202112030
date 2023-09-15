package Convertor;
public class Empty extends Instruction {
    public Empty() {
        super("EMPTY");
    }
    public String convert(int tab) {
        return "";
    }
}