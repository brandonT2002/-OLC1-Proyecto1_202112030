package Graphs;
import TableSym.DataSym;
import TableSym.TableSym;
public abstract class Value {
    public String name;
    public Value(String name) {
        this.name = name;
    }
    public abstract DataSym init(EnvGraph envG, TableSym tableSym);
}