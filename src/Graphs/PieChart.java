package Graphs;
import javax.swing.*;

import TableSym.DataSym;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PieChart extends JPanel {

    private List<Double> data = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private String title;
    private List<Color> colors;

    public PieChart(ArrayList<DataSym> data, ArrayList<DataSym> titles, String title) {
        for(DataSym d : data) {
            this.data.add(d.dataD);;
        }
        for(DataSym t : titles) {
            this.titles.add(t.dataS);
        }
        this.title = title;

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

    public PieChart(List<Double> data, List<String> titles, String title) {
        this.data = data;
        this.titles = titles;
        this.title = title;

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

        int width = getWidth();
        int height = getHeight();
        int margin = 50; // Margen alrededor del gráfico
        int radio = Math.min(width, height) / 2 - margin;

        // Calcular el total de los datos para calcular porcentajes
        double total = data.stream().mapToDouble(Number::doubleValue).sum();

        // Dibujar el título
        g.setColor(Color.WHITE);
        g.setFont(new Font("Montserrat", Font.BOLD, 25)); // Fuente Montserrat, Tamaño 25, Negrita
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (width - titleWidth) / 2, 20);

        // Dibujar cada sector del gráfico de pie
        int startAngle = 0;
        for (int i = 0; i < data.size(); i++) {
            double porcentage = data.get(i).doubleValue() / total;
            int angle = (int) (porcentage * 360);

            g.setColor(colors.get(i % colors.size()));
            g.fillArc(width / 2 - radio, height / 2 - radio, radio * 2, radio * 2, startAngle, angle);

            // Dibuja el valor de porcentaje junto al sector
            int xText = (int) (width / 2 + radio * 0.8 * Math.cos(Math.toRadians(startAngle + angle / 2)));
            int yText = (int) (height / 2 - radio * 0.8 * Math.sin(Math.toRadians(startAngle + angle / 2)));
            String textPorcentage = String.format("%.1f%%", porcentage * 100);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Montserrat", Font.PLAIN, 12));
            int textWidth = g.getFontMetrics().stringWidth(textPorcentage);
            g.drawString(textPorcentage, xText - textWidth / 2, yText);

            startAngle += angle;
        }

        // Dibujar etiquetas en el panel de títulos
        int panelTitlesX = width - 150; // Posición X del panel de títulos
        int panelTitlesY = 50; // Posición Y del panel de títulos
        int panelTitlesWidth = 150; // Ancho del panel de títulos

        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(panelTitlesX, panelTitlesY, panelTitlesWidth, height - 2 * margin);

        g.setFont(new Font("Montserrat", Font.PLAIN, 17)); // Fuente Montserrat, Tamaño 17, Texto plano

        for (int i = 0; i < titles.size(); i++) {
            String labels = titles.get(i);
            g.setColor(colors.get(i % colors.size()));
            g.fillRect(panelTitlesX + 10, panelTitlesY + 30 * i + 10, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString(labels, panelTitlesX + 40, panelTitlesY + 30 * i + 30);
        }
    }
}
