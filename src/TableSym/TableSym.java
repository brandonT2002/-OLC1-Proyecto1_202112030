package TableSym;
import java.util.Map;
import java.util.TreeMap;

public class TableSym {
    public Map<String, Map<String, DataSym>> filesJSON;
    public TableSym() {
        filesJSON = new TreeMap<>();
    }
    public void addFile(String nameFile) {
        filesJSON.put(nameFile, new TreeMap<>());
    }
    public void addAttribute(String nameFile, String attribute, String value, boolean isString) {
        Map<String, DataSym> fileAttributes = filesJSON.get(nameFile);
        if(isString) {
            fileAttributes.put(attribute, new DataSym(isString, value));
            return;
        }
        fileAttributes.put(attribute, new DataSym(isString, Double.parseDouble(value)));
    }
    public void displayValues() {
    for (Map.Entry<String, Map<String, DataSym>> fileEntry : filesJSON.entrySet()) {
        String fileName = fileEntry.getKey();
        Map<String, DataSym> fileAttributes = fileEntry.getValue();
        
        System.out.println("Archivo: " + fileName);
        
        for (Map.Entry<String, DataSym> attributeEntry : fileAttributes.entrySet()) {
            String attributeName = attributeEntry.getKey();
            DataSym attributeValue = attributeEntry.getValue();
            
            System.out.printf("\t %-8s %20s \n",attributeName,attributeValue);
        }
        
        System.out.println(); // Línea en blanco para separar archivos
        }
    }
    public DataSym getValue(String nameFile, String attribute) {;
        if (filesJSON.containsKey(nameFile)) {
            Map<String, DataSym> fileAttributes = filesJSON.get(nameFile);
            if (fileAttributes.containsKey(attribute)) {
                return fileAttributes.get(attribute);
            }
        }
        return null;
    }
}