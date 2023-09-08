package Env;
import java.util.Map;
import java.util.TreeMap;

public class Env {
    Map<String, Map<String, Object>> filesJSON;
    public Env() {
        filesJSON = new TreeMap<>();
    }
    public void addFile(String nameFile) {
        filesJSON.put(nameFile, new TreeMap<>());
    }
    public void addAttributes(String nameFile, String attribute, Object value) {
        Map<String, Object> fileAttributes = filesJSON.get(nameFile);
        fileAttributes.put(attribute, value);
    }
    public void displayValues() {
    for (Map.Entry<String, Map<String, Object>> fileEntry : filesJSON.entrySet()) {
        String fileName = fileEntry.getKey();
        Map<String, Object> fileAttributes = fileEntry.getValue();
        
        System.out.println("Archivo: " + fileName);
        
        for (Map.Entry<String, Object> attributeEntry : fileAttributes.entrySet()) {
            String attributeName = attributeEntry.getKey();
            Object attributeValue = attributeEntry.getValue();
            
            System.out.printf("\t %-8s %20s \n",attributeName,attributeValue);
        }
        
        System.out.println(); // Línea en blanco para separar archivos
        }
    }
    public Object getValue(String nameFile, String attribute) {;
        if (filesJSON.containsKey(nameFile)) {
            Map<String, Object> fileAttributes = filesJSON.get(nameFile);
            if (fileAttributes.containsKey(attribute)) {
                return fileAttributes.get(attribute);
            }
        }
        return null;
    }
}