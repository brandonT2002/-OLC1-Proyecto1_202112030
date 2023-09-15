package Graphs;
import TableSym.DataSym;
import TableSym.TableSym;
public class SymbStat extends Value {
    public String filename;
    public String attrib;
    public SymbStat(String filename, String attrib) {
        super("SYMB_STAT");
        this.filename = filename;
        this.attrib = attrib;
    }
    public DataSym init(EnvGraph envG, TableSym tableSym) {
        return tableSym.getValue(filename, attrib.toLowerCase());
    }
}
