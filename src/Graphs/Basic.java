package Graphs;

import TableSym.DataSym;
import TableSym.TableSym;

public class Basic extends Value {
    public DataSym value;
    public Basic(DataSym value) {
        super("BASIC");
        this.value = value;
    }
    public DataSym init(EnvGraph symbols, TableSym tableSym) {
        return value;
    }
}