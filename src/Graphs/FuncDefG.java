package Graphs;
import java.util.ArrayList;
import TableSym.TableSym;
public class FuncDefG {
    public ArrayList<Init> inits;
    public FuncDefG(ArrayList<Init> inits) {
        this.inits = inits;
    }
    public void define(EnvGraph envG, TableSym tableSym) {
        for(Init init : inits) {
            init.init(envG, tableSym);
        }
    }
}
