package Graphs;
import TableSym.TableSym;
public class InitLabel extends Init {
    public String id;
    public Value value;
    public InitLabel(String id, Value value) {
        super("INIT_LABEL");
        this.id = id;
        this.value = value;
    }
    public void init(EnvGraph envG, TableSym tableSym) {
        envG.saveDataSym(id, value.init(envG, tableSym));
    }
}