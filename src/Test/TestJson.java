package Test;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import Env.Env;
import Graphs.BarGraph;

public class TestJson {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        Env table = new Env();
        table.addFile("File1.json");
        table.addAttributes("File1.json", "Titulo", "Grafica de Barras");
        table.addAttributes("File1.json", "Eje X", "Deportes");
        table.addAttributes("File1.json", "Eje Y", "Integrantes");
        table.addAttributes("File1.json", "Valor1", 400);
        table.addAttributes("File1.json", "Valor2", 20);
        table.addAttributes("File1.json", "Valor3", 100);
        table.addAttributes("File1.json", "Etiqueta1", "Real Madrid");
        table.addAttributes("File1.json", "Etiqueta2", "Barcelona");
        table.addAttributes("File1.json", "Etiqueta3", "PSG");

        List<Number> alturas = new ArrayList<>();
            alturas.add((Number) table.getValue("File1.json", "Valor1"));
            alturas.add((Number) table.getValue("File1.json", "Valor2"));
            alturas.add((Number) table.getValue("File1.json", "Valor3"));

        List<String> etiquetas = new ArrayList<>();
            etiquetas.add((String) table.getValue("File1.json", "Etiqueta1"));
            etiquetas.add((String) table.getValue("File1.json", "Etiqueta2"));
            etiquetas.add((String) table.getValue("File1.json", "Etiqueta3"));

        graph(alturas, etiquetas, (String) table.getValue("File1.json", "Titulo"), (String) table.getValue("File1.json", "Eje X"), (String) table.getValue("File1.json", "Eje Y"));

        /*table.addFile("Project.json");
        table.addAttributes("Project.json", "Titulo", "Grafica de Pie");
        table.addAttributes("Project.json", "Valor1", 20);
        table.addAttributes("Project.json", "Valor2", 15.75);*/
        // table.displayValues();

        // System.out.println(table.getValue("File1.json", "Valor1"));
        // System.out.println(table.getValue("File2.json", "Valor1"));
    }

    public static void graph(List<Number> alturas, List<String> etiquetas, String titulo, String tituloX, String tituloY) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfica de Barras");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel panel = new JPanel(new BorderLayout());

            BarGraph grafica = new BarGraph(alturas, etiquetas, titulo, tituloX, tituloY);
            panel.add(grafica);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
