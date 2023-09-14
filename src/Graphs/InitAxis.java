package Graphs;
import java.util.ArrayList;

import TableSym.DataSym;
import TableSym.TableSym;
public class InitAxis extends Init {
    public String id;
    public ArrayList<Value> values;
    public InitAxis(String id, ArrayList<Value> values) {
        super("INIT_AXIS");
        this.id = id;
        this.values = values;
    }
    public void init(EnvGraph envG, TableSym tableSym) {
        ArrayList<DataSym> values = new ArrayList<>();
        for(Value v : this.values) {
            values.add(v.init(envG, tableSym));
        }
        envG.saveArrDataSym(id, values);
    }
}