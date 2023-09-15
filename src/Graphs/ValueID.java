package Graphs;
import TableSym.DataSym;
import TableSym.TableSym;
public class ValueID extends Value {
    public String id;
    public ValueID(String id) {
        super("VALUE_ID");
        this.id = id;
    }
    public DataSym init(EnvGraph envG, TableSym tableSym) {
        return envG.getDataSym(id);
    }
}