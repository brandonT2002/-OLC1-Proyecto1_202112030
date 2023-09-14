package Graphs;
import TableSym.TableSym;
public abstract class Init {
    public String name;
    public Init(String name) {
        this.name = name;
    }
    public abstract void init(EnvGraph envG, TableSym tableSym);
}
