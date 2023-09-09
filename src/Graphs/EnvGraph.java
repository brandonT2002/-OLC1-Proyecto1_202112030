package Graphs;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import TableSym.DataSym;

public class EnvGraph {
    Map<String, DataSym> ids = new TreeMap<>();
    Map<String, ArrayList<DataSym>> arrs = new TreeMap<>();

    public void saveDataSym(String id, DataSym dts) {
        if (!ids.containsKey(id)) {
            // System.out.println("------------GUARDA------------");
            // System.out.println("\"" + id + "\"" + " = " + dts);
            ids.put(id, dts);
        }
    }
    
    public DataSym getDataSym(String id) {
        if (ids.containsKey(id)) {
            // System.out.println("============OBTIENE============");
            // System.out.println("\"" + id + "\"" + " = " + ids.get(id));
            return ids.get(id);
        }
        return null;
    }
    
    public void saveArrDataSym(String id, ArrayList<DataSym> values) {
        if (!arrs.containsKey(id)) {
            // System.out.println("------------GUARDA------------");
            // System.out.println("\"" + id + "\"" + " = " + values);
            arrs.put(id, values);
        }
    }

    public ArrayList<DataSym> getArrDataSym(String id) {
        if (arrs.containsKey(id)) {
            // System.out.println("============OBTIENE============");
            // System.out.println("\"" + id + "\"" + " = " + arrs.get(id));
            return arrs.get(id);
        }
        return null;
    }
}
