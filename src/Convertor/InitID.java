package Convertor;
public class InitID extends Instruction {
    private String id;
    private Expression value;
    public InitID(String id, Expression value) {
        super("INITID");
        this.id = id;
        this.value = value;
    }
    public String convert(int tab) {
        String tabs = "\t".repeat(tab);
        if(value != null) {
            return tabs + this.id + " = " + this.value.convert();
        }
        return tabs + this.id;
    }
}