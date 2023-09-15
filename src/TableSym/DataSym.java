package TableSym;

public class DataSym {
    boolean isString;
    public String dataS;
    public double dataD;
    public DataSym(boolean isString, String dataS) {
        this.isString = isString;
        this.dataS = dataS;
    }
    public DataSym(boolean isString, double dataD) {
        this.isString = isString;
        this.dataD = dataD;
    }
    public String toString() {
        if(isString) {
            return "DataSym(" + this.isString + ", " + dataS + ")";
        }
        return "DataSym(" + this.isString + ", " + dataD + ")";
    }
}