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

import Graphs.BarGraph;
import Graphs.PieChart;
import TableSym.*;

public class TestJson {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        TableSym table = new TableSym();
        table.addFile("File1.json");
        table.addAttribute("File1.json", "Titulo", "Grafica de Barras", true);
        table.addAttribute("File1.json", "Titulo1", "Grafica de Pie", true);
        table.addAttribute("File1.json", "Eje X", "Deportes", true);
        table.addAttribute("File1.json", "Eje Y", "Integrantes", true);
        table.addAttribute("File1.json", "Valor1", "14", false);
        table.addAttribute("File1.json", "Valor2","5", false);
        table.addAttribute("File1.json", "Valor3","1", false);
        table.addAttribute("File1.json", "Etiqueta1", "Real Madrid", true);
        table.addAttribute("File1.json", "Etiqueta2", "Barcelona", true);
        table.addAttribute("File1.json", "Etiqueta3", "Manchester City", true);

        List<Double> alturas = new ArrayList<>();
            alturas.add(table.getValue("File1.json", "Valor1").dataD);
            alturas.add(table.getValue("File1.json", "Valor2").dataD);
            alturas.add(table.getValue("File1.json", "Valor3").dataD);

        List<String> etiquetas = new ArrayList<>();
            etiquetas.add(table.getValue("File1.json", "Etiqueta1").dataS);
            etiquetas.add(table.getValue("File1.json", "Etiqueta2").dataS);
            etiquetas.add(table.getValue("File1.json", "Etiqueta3").dataS);

        barGraph(alturas, etiquetas, table.getValue("File1.json", "Titulo").dataS, table.getValue("File1.json", "Eje X").dataS, table.getValue("File1.json", "Eje Y").dataS);

        List<Double> datos = new ArrayList<>();
            datos.add(table.getValue("File1.json", "Valor1").dataD);
            datos.add(table.getValue("File1.json", "Valor2").dataD);
            datos.add(table.getValue("File1.json", "Valor3").dataD);

        List<String> titulos = new ArrayList<>();
            titulos.add(table.getValue("File1.json", "Etiqueta1").dataS);
            titulos.add(table.getValue("File1.json", "Etiqueta2").dataS);
            titulos.add(table.getValue("File1.json", "Etiqueta3").dataS);

        pieChart(datos, titulos, table.getValue("File1.json", "Titulo1").dataS);


        /*table.addFile("Project.json");
        table.addAttributes("Project.json", "Titulo", "Grafica de Pie");
        table.addAttributes("Project.json", "Valor1", 20);
        table.addAttributes("Project.json", "Valor2", 15.75);*/
        // table.displayValues();

        // System.out.println(table.getValue("File1.json", "Valor1"));
        // System.out.println(table.getValue("File2.json", "Valor1"));
    }

    public static void barGraph(List<Double> alturas, List<String> etiquetas, String titulo, String tituloX, String tituloY) {
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

    public static void pieChart(List<Double> datos, List<String> titulos, String titulo) {
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
