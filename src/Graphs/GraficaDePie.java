package Graphs;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraficaDePie extends JPanel {

    private List<Double> datos;
    private List<Color> colores;
    private List<String> titulos;
    private String titulo;

    public GraficaDePie(List<Double> datos, List<String> titulos, List<Color> colores, String titulo) {
        this.datos = datos;
        this.titulos = titulos;
        this.colores = colores;
        this.titulo = titulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int ancho = getWidth();
        int alto = getHeight();
        int margen = 50; // Margen alrededor del gráfico
        int radio = Math.min(ancho, alto) / 2 - margen;

        // Calcular el total de los datos para calcular porcentajes
        double total = datos.stream().mapToDouble(Double::doubleValue).sum();

        // Dibujar el título
        g.setColor(Color.WHITE);
        g.setFont(new Font("Montserrat", Font.BOLD, 25)); // Fuente Montserrat, Tamaño 25, Negrita
        int tituloWidth = g.getFontMetrics().stringWidth(titulo);
        g.drawString(titulo, (ancho - tituloWidth) / 2, 20);

        // Dibujar cada sector del gráfico de pie
        int inicioAngulo = 0;
        for (int i = 0; i < datos.size(); i++) {
            double porcentaje = datos.get(i) / total;
            int angulo = (int) (porcentaje * 360);

            g.setColor(colores.get(i % colores.size()));
            g.fillArc(ancho / 2 - radio, alto / 2 - radio, radio * 2, radio * 2, inicioAngulo, angulo);

            // Dibuja el valor de porcentaje junto al sector
            int xTexto = (int) (ancho / 2 + radio * 0.8 * Math.cos(Math.toRadians(inicioAngulo + angulo / 2)));
            int yTexto = (int) (alto / 2 - radio * 0.8 * Math.sin(Math.toRadians(inicioAngulo + angulo / 2)));
            String porcentajeTexto = String.format("%.1f%%", porcentaje * 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Montserrat", Font.PLAIN, 12));
            int textoWidth = g.getFontMetrics().stringWidth(porcentajeTexto);
            g.drawString(porcentajeTexto, xTexto - textoWidth / 2, yTexto);

            inicioAngulo += angulo;
        }

        // Dibujar etiquetas en el panel de títulos
        int panelTitulosX = ancho - 150; // Posición X del panel de títulos
        int panelTitulosY = 50; // Posición Y del panel de títulos
        int panelTitulosAncho = 150; // Ancho del panel de títulos

        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(panelTitulosX, panelTitulosY, panelTitulosAncho, alto - 2 * margen);

        g.setFont(new Font("Montserrat", Font.PLAIN, 17)); // Fuente Montserrat, Tamaño 17, Texto plano

        for (int i = 0; i < titulos.size(); i++) {
            String etiqueta = titulos.get(i);
            g.setColor(colores.get(i % colores.size()));
            g.fillRect(panelTitulosX + 10, panelTitulosY + 30 * i + 10, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString(etiqueta, panelTitulosX + 40, panelTitulosY + 30 * i + 30);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfico de Pie Personalizado");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            List<Double> datos = new ArrayList<>();
            datos.add(30.0);
            datos.add(20.0);
            datos.add(15.0);
            datos.add(10.0);
            datos.add(25.0);

            List<String> titulos = new ArrayList<>();
            titulos.add("Segmento A");
            titulos.add("Segmento B");
            titulos.add("Segmento C");
            titulos.add("Segmento D");
            titulos.add("Segmento E");

            List<Color> colores = new ArrayList<>();
            colores.add(Color.decode("#CC4125"));
            colores.add(Color.decode("#E06666"));
            colores.add(Color.decode("#F6B26B"));
            colores.add(Color.decode("#FFD966"));
            colores.add(Color.decode("#93C47D"));
            colores.add(Color.decode("#76A5AF"));
            colores.add(Color.decode("#6D9EEB"));
            colores.add(Color.decode("#6FA8DC"));
            colores.add(Color.decode("#8E7CC3"));
            colores.add(Color.decode("#C27BA0"));

            String titulo = "Distribución de Datos";

            GraficaDePie grafico = new GraficaDePie(datos, titulos, colores, titulo);
            frame.add(grafico);

            frame.setVisible(true);
        });
    }
}
