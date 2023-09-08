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
import Graphs.PieChart;

public class TestJson {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        Env table = new Env();
        table.addFile("File1.json");
        table.addAttributes("File1.json", "Titulo", "Grafica de Barras");
        table.addAttributes("File1.json", "Titulo1", "Grafica de Pie");
        table.addAttributes("File1.json", "Eje X", "Deportes");
        table.addAttributes("File1.json", "Eje Y", "Integrantes");
        table.addAttributes("File1.json", "Valor1", 14);
        table.addAttributes("File1.json", "Valor2", 5);
        table.addAttributes("File1.json", "Valor3", 1);
        table.addAttributes("File1.json", "Etiqueta1", "Real Madrid");
        table.addAttributes("File1.json", "Etiqueta2", "Barcelona");
        table.addAttributes("File1.json", "Etiqueta3", "Manchester City");

        List<Number> alturas = new ArrayList<>();
            alturas.add((Number) table.getValue("File1.json", "Valor1"));
            alturas.add((Number) table.getValue("File1.json", "Valor2"));
            alturas.add((Number) table.getValue("File1.json", "Valor3"));

        List<String> etiquetas = new ArrayList<>();
            etiquetas.add((String) table.getValue("File1.json", "Etiqueta1"));
            etiquetas.add((String) table.getValue("File1.json", "Etiqueta2"));
            etiquetas.add((String) table.getValue("File1.json", "Etiqueta3"));

        barGraph(alturas, etiquetas, (String) table.getValue("File1.json", "Titulo"), (String) table.getValue("File1.json", "Eje X"), (String) table.getValue("File1.json", "Eje Y"));

        List<Number> datos = new ArrayList<>();
            datos.add((Number) table.getValue("File1.json", "Valor1"));
            datos.add((Number) table.getValue("File1.json", "Valor2"));
            datos.add((Number) table.getValue("File1.json", "Valor3"));

        List<String> titulos = new ArrayList<>();
            titulos.add((String) table.getValue("File1.json", "Etiqueta1"));
            titulos.add((String) table.getValue("File1.json", "Etiqueta2"));
            titulos.add((String) table.getValue("File1.json", "Etiqueta3"));

        pieChart(datos, titulos, (String) table.getValue("File1.json", "Titulo1"));


        /*table.addFile("Project.json");
        table.addAttributes("Project.json", "Titulo", "Grafica de Pie");
        table.addAttributes("Project.json", "Valor1", 20);
        table.addAttributes("Project.json", "Valor2", 15.75);*/
        // table.displayValues();

        // System.out.println(table.getValue("File1.json", "Valor1"));
        // System.out.println(table.getValue("File2.json", "Valor1"));
    }

    public static void barGraph(List<Number> alturas, List<String> etiquetas, String titulo, String tituloX, String tituloY) {
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

    public static void pieChart(List<Number> datos, List<String> titulos, String titulo) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfico de Pie Personalizado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            PieChart grafico = new PieChart(datos, titulos, titulo);
            frame.add(grafico);

            frame.setVisible(true);
        });
    }
}
