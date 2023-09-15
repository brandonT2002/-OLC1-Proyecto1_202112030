package Graphs;
import javax.swing.*;

import TableSym.DataSym;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BarGraph extends JPanel {
    private List<Double> heights = new ArrayList<>();
    private List<String> xAxis = new ArrayList<>();
    private String title;
    private String xTitle;
    private String yTitle;
    private List<Color> colors;

    public BarGraph(ArrayList<DataSym> heights, ArrayList<DataSym> labels, String title, String xTitle, String yTitle) {
        for(DataSym data : heights) {
            this.heights.add(data.dataD);
        }
        for(DataSym data : labels) {
            this.xAxis.add(data.dataS);
        }

        this.title = title;
        this.xTitle = xTitle;
        this.yTitle = yTitle;
        
        colors = new ArrayList<>();
        colors.add(Color.decode("#CC4125"));
        colors.add(Color.decode("#E06666"));
        colors.add(Color.decode("#F6B26B"));
        colors.add(Color.decode("#FFD966"));
        colors.add(Color.decode("#93C47D"));
        colors.add(Color.decode("#76A5AF"));
        colors.add(Color.decode("#6D9EEB"));
        colors.add(Color.decode("#6FA8DC"));
        colors.add(Color.decode("#8E7CC3"));
        colors.add(Color.decode("#C27BA0"));

    }

    public BarGraph(List<Double> heights, List<String> xAxis, String title, String xTitle, String yTitle) {
        this.heights = heights;
        this.xAxis = xAxis;
        this.title = title;
        this.xTitle = xTitle;
        this.yTitle = yTitle;
        
        colors = new ArrayList<>();
        colors.add(Color.decode("#CC4125"));
        colors.add(Color.decode("#E06666"));
        colors.add(Color.decode("#F6B26B"));
        colors.add(Color.decode("#FFD966"));
        colors.add(Color.decode("#93C47D"));
        colors.add(Color.decode("#76A5AF"));
        colors.add(Color.decode("#6D9EEB"));
        colors.add(Color.decode("#6FA8DC"));
        colors.add(Color.decode("#8E7CC3"));
        colors.add(Color.decode("#C27BA0"));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 80;
        int y = getHeight() - 45;
        int barWidth = (getWidth() - 100) / (heights.size() + 2);

        // Dibujar el título
        g.setColor(Color.WHITE);
        g.setFont(new Font("Montserrat", Font.BOLD, 25)); // Usamos Montserrat como fuente
        g.drawString(title, getWidth() / 2 - title.length() * 4, 30);

        // Dibujar etiquetas de los ejes
        g.setFont(new Font("Montserrat", Font.PLAIN, 17)); // Usamos Montserrat como fuente
        g.drawString(xTitle, getWidth() / 2 - xTitle.length() * 3, y + 30);
        g.drawString(yTitle, barWidth - 50, y / 2);

        // Obtener el valor máximo de alturas para ajustar la escala del eje Y
        int maxHeight = heights.stream().mapToInt(Number::intValue).max().orElse(0);
        int yScale = calcularEscalaY(maxHeight);

        // Dibujar escala en el eje Y basada en la escala calculada
        for (int i = 0; i <= maxHeight; i += yScale) {
            int yPos = y - (i * (getHeight() - 125) / maxHeight);
            g.setColor(Color.WHITE);
            g.drawLine(40 + barWidth, yPos, 50 + barWidth, yPos);
            g.drawString(Integer.toString(i), 15 + barWidth, yPos);
        }

        for (int i = 0; i < heights.size(); i++) {
            Number altura = heights.get(i);
            int barHeight = (int) (altura.doubleValue() * (getHeight() - 125) / maxHeight); // Ajuste de la altura
            
            // Obtener el color de la lista de colores
            Color barColor = colors.get(i % colors.size());
            
            g.setColor(barColor);
            g.fillRect(x + barWidth, y - barHeight, barWidth, barHeight);
            
            g.setColor(new Color(0, 0, 0, 0));
            g.drawRect(x + barWidth, y - barHeight, barWidth, barHeight);

            // Etiquetas para las barras
            g.setColor(Color.WHITE);
            g.drawString(xAxis.get(i), x + barWidth, y + 15);
            
            // Valor de la barra sobre la barra
            String etiqueta = String.valueOf(heights.get(i));
            FontMetrics fm = g.getFontMetrics();
            int etiquetaWidth = fm.stringWidth(etiqueta);
            int etiquetaX = x + barWidth + (barWidth - etiquetaWidth) / 2;
            g.drawString(etiqueta, etiquetaX, y - barHeight - 5);
            
            x += barWidth + 10;
        }
    }

    private int calcularEscalaY(int maxHeight) {
        if (maxHeight <= 5) {
            return 2;
        } else if (maxHeight <= 10) {
            return 2;
        } else if (maxHeight <= 50) {
            return 5;
        } else if (maxHeight <= 100) {
            return 10;
        } else {
            return 100;
        }
    }
}
