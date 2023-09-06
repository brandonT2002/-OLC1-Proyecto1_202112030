package Convertor;
public abstract class Instruction {
    public String name;
    public Instruction(String name) {
        this.name = name;
    }
    public abstract String convert(int tab);
}