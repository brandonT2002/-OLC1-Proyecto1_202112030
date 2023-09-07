package Graphs;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GraficaDeBarras extends JPanel {
    private List<Number> alturas;
    private List<String> etiquetas;
    private String titulo;
    private String etiquetaEjeX;
    private String etiquetaEjeY;
    private List<Color> colores;

    public GraficaDeBarras(List<Number> alturas, List<String> etiquetas, String titulo, String etiquetaEjeX, String etiquetaEjeY) {
        this.alturas = alturas;
        this.etiquetas = etiquetas;
        this.titulo = titulo;
        this.etiquetaEjeX = etiquetaEjeX;
        this.etiquetaEjeY = etiquetaEjeY;
        
        colores = new ArrayList<>();
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

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 80;
        int y = getHeight() - 45;
        int barWidth = (getWidth() - 100) / (alturas.size() + 2);

        // Dibujar el título
        g.setColor(Color.WHITE);
        g.setFont(new Font("Montserrat", Font.BOLD, 25)); // Usamos Montserrat como fuente
        g.drawString(titulo, getWidth() / 2 - titulo.length() * 4, 30);

        // Dibujar etiquetas de los ejes
        g.setFont(new Font("Montserrat", Font.PLAIN, 17)); // Usamos Montserrat como fuente
        g.drawString(etiquetaEjeX, getWidth() / 2 - etiquetaEjeX.length() * 3, y + 30);
        g.drawString(etiquetaEjeY, barWidth - 50, y / 2);

        // Obtener el valor máximo de alturas para ajustar la escala del eje Y
        int maxAltura = alturas.stream().mapToInt(Number::intValue).max().orElse(0);

        // Dibujar escala en el eje Y basada en el valor máximo de alturas
        for (int i = 0; i <= maxAltura; i += 50) {
            int yPos = y - (i * (getHeight() - 100) / maxAltura);
            g.setColor(Color.WHITE);
            g.drawLine(40 + barWidth, yPos, 50 + barWidth, yPos);
            g.drawString(Integer.toString(i), 15 + barWidth, yPos);
        }

        for (int i = 0; i < alturas.size(); i++) {
            Number altura = alturas.get(i);
            int barHeight = (int) (altura.doubleValue() * (getHeight() - 100) / maxAltura); // Ajuste de la altura
            
            // Obtener el color de la lista de colores
            Color barColor = colores.get(i % colores.size());
            
            g.setColor(barColor);
            g.fillRect(x + barWidth, y - barHeight, barWidth, barHeight);
            
            g.setColor(new Color(0, 0, 0, 0));
            g.drawRect(x + barWidth, y - barHeight, barWidth, barHeight);

            // Etiquetas para las barras
            g.setColor(Color.WHITE);
            g.drawString(etiquetas.get(i), x + barWidth, y + 15);
            
            // Valor de la barra sobre la barra
            String etiqueta = Integer.toString(altura.intValue());
            FontMetrics fm = g.getFontMetrics();
            int etiquetaWidth = fm.stringWidth(etiqueta);
            int etiquetaX = x + barWidth + (barWidth - etiquetaWidth) / 2;
            g.drawString(etiqueta, etiquetaX, y - barHeight - 5);
            // g.drawString(Integer.toString(altura.intValue()), x + barWidth, y - barHeight - 5);
            
            x += barWidth + 10;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
        } catch (UnsupportedLookAndFeelException ex) {
        }
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gráfica de Barras");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            JPanel panel = new JPanel(new BorderLayout());

            List<Number> alturas = new ArrayList<>();
            alturas.add(100);
            alturas.add(150);
            alturas.add(200);
            alturas.add(250);
            alturas.add(300);

            List<String> etiquetas = new ArrayList<>();
            etiquetas.add("Barra 1");
            etiquetas.add("Barra 2");
            etiquetas.add("Barra 3");
            etiquetas.add("Barra 4");
            etiquetas.add("Barra 5");

            GraficaDeBarras grafica = new GraficaDeBarras(alturas, etiquetas, "Gráfica de Barras", "Barras", "Altura");
            panel.add(grafica);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
}
